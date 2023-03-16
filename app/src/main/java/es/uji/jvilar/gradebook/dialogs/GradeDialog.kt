package es.uji.jvilar.gradebook.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import es.uji.jvilar.gradebook.R
import es.uji.jvilar.gradebook.database.Grade
import es.uji.jvilar.gradebook.database.Subject
import java.lang.IllegalStateException

class GradeDialog : DialogFragment() {
    interface GradeListener {
        fun onGradeAvailable(grade: Grade)
    }

    lateinit var subjectSpinner: Spinner
    lateinit var gradeEditText: EditText
    lateinit var weightEditText: EditText
    lateinit var subjects: List<Subject>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = this.activity ?: throw IllegalStateException("Activity cannot be null")

        val view = activity.layoutInflater.inflate(R.layout.dialog_grade, null)
        with (view) {
            subjectSpinner = findViewById(R.id.subjectCode)
            gradeEditText = findViewById(R.id.grade)
            weightEditText = findViewById(R.id.weight)
        }

        subjects = requireArguments().let {
            @Suppress("DEPRECATION")
            if (SDK_INT >= 33)
                it.getParcelableArrayList(SUBJECTS, Subject::class.java)!!
            else
                it.getParcelableArrayList(SUBJECTS)!!
        }
        val labels = subjects.map {"${it.code} - ${it.name}"}
        subjectSpinner.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, labels)

        return AlertDialog.Builder(activity).run {
            setView(view)
            setNegativeButton(android.R.string.cancel, null)
            setPositiveButton(android.R.string.ok) { _, _ -> onGradeReady() }
            create()
        }
    }

    private fun onGradeReady() {
        val listener = activity as GradeListener?
        try {
            val grade = gradeEditText.text.toString().toDouble()
            val weight = weightEditText.text.toString().toDouble()
            val subject = subjects[subjectSpinner.selectedItemPosition]
            listener!!.onGradeAvailable(Grade(subject.code, weight, grade))
        } catch (e: NumberFormatException) {
            Toast.makeText(context, "Grade not stored", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val SUBJECTS = "SUBJECTS"
    }
}