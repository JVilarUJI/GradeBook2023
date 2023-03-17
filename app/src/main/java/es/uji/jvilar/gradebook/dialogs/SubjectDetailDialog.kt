package es.uji.jvilar.gradebook.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.uji.jvilar.gradebook.DetailAdapter
import es.uji.jvilar.gradebook.R
import es.uji.jvilar.gradebook.database.Grade
import es.uji.jvilar.gradebook.database.Subject
import java.lang.IllegalStateException

class SubjectDetailDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = activity ?: throw IllegalStateException("Activity cannot be null")

        val subject: Subject = requireArguments().let {
            @Suppress("DEPRECATION")
            if (Build.VERSION.SDK_INT >= 33)
                it.getParcelable(SUBJECT, Subject::class.java)!!
            else
                it.getParcelable(SUBJECT)!!
        }
        val grades: List<Grade> = requireArguments().let {
            @Suppress("DEPRECATION")
            if (Build.VERSION.SDK_INT >= 33)
                it.getParcelableArrayList(GRADES, Grade::class.java)!!
            else
                it.getParcelableArrayList(GRADES)!!
        }

        val view = activity.layoutInflater.inflate(R.layout.dialog_subject_detail, null)

        view.findViewById<TextView>(R.id.noGradesText).apply {
            visibility = if (grades.isEmpty())
                View.VISIBLE
            else
                View.GONE
        }
        view.findViewById<RecyclerView>(R.id.gradeList).apply {
            if (grades.isEmpty())
                visibility = View.GONE
            else {
                adapter = DetailAdapter(grades)
                layoutManager = LinearLayoutManager(activity)
                visibility = View.VISIBLE
            }
        }

        return AlertDialog.Builder(activity).run {
            setView(view)
            setTitle(subject.name)
            setNeutralButton(android.R.string.ok, null)
            create()
        }
    }

    companion object {
        const val SUBJECT = "SUBJECT"
        const val GRADES = "GRADES"
    }
}