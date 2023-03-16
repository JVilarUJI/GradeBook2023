package es.uji.jvilar.gradebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.jvilar.gradebook.database.SubjectGrade

class SubjectAdapter(private val subjects: List<SubjectGrade>):
    RecyclerView.Adapter<SubjectAdapter.ViewHolder> () {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subjectCode: TextView = view.findViewById(R.id.subjectCode)
        val subjectName: TextView = view.findViewById(R.id.subjectName)
        val finalGrade: TextView = view.findViewById(R.id.finalGrade)
        val predictedGrade: TextView = view.findViewById(R.id.predictedGrade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject_line, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(subjects[position]) {
            holder.subjectCode.text = subject.code
            holder.subjectName.text = subject.name

            val wSum = "%.2f".format(weightSum)

            if (wSum == "0.00") {
                holder.finalGrade.visibility = View.GONE
                holder.predictedGrade.visibility = View.GONE
            } else {
                holder.finalGrade.visibility = View.VISIBLE
                holder.finalGrade.text = "%.2f".format(gradeSum)
                if (wSum == "1.00") {
                    holder.predictedGrade.visibility = View.GONE
                } else {
                    holder.predictedGrade.visibility = View.VISIBLE
                    holder.predictedGrade.text = "%.2f".format(gradeSum / weightSum)
                }
            }
        }
    }

    override fun getItemCount() = subjects.size
}