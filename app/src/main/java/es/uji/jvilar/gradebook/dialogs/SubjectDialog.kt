package es.uji.jvilar.gradebook.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import es.uji.jvilar.gradebook.R
import es.uji.jvilar.gradebook.database.Subject
import java.lang.IllegalStateException

class SubjectDialog: DialogFragment() {
    interface SubjectListener {
        fun onSubjectAvailable(subject: Subject)
    }

    private lateinit var codeEditText: EditText
    private lateinit var nameEditText: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity: Activity = activity ?: throw IllegalStateException("Activity cannot be null")
        val builder = AlertDialog.Builder(activity)
        val view: View = activity.layoutInflater.inflate(R.layout.dialog_subject, null)
        nameEditText = view.findViewById(R.id.subjectName)
        codeEditText = view.findViewById(R.id.subjectCode)
        builder.setView(view)
            .setNegativeButton(android.R.string.cancel, null)
            .setPositiveButton(android.R.string.ok) { _, _ -> onSubjectReady() }
        return builder.create()
    }

    private fun onSubjectReady() {
        val listener = activity as SubjectListener?
        val code = codeEditText.text.toString()
        val name = nameEditText.text.toString()
        if (code == "") {
            Toast.makeText(context, "No code given, subject not inserted", Toast.LENGTH_LONG).show()
            return
        }
        if (name == "") {
            Toast.makeText(context, "No name given, subject not inserted", Toast.LENGTH_LONG).show()
            return
        }
        val subject = Subject(code, name)
        listener!!.onSubjectAvailable(subject)
    }
}
