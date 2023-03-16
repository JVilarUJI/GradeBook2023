package es.uji.jvilar.gradebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.jvilar.gradebook.database.Subject

class SubjectAdapter(private val subjects: List<Subject>):
    RecyclerView.Adapter<SubjectAdapter.ViewHolder> () {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subjectCode: TextView = view.findViewById(R.id.subjectCode)
        val subjectName: TextView = view.findViewById(R.id.subjectName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject_line, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(subjects[position]) {
            holder.subjectCode.text = code
            holder.subjectName.text = name
        }
    }

    override fun getItemCount() = subjects.size
}