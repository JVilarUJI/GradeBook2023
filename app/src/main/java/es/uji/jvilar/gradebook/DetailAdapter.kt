package es.uji.jvilar.gradebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.jvilar.gradebook.database.Grade

class DetailAdapter(private val grades: List<Grade>):
    RecyclerView.Adapter<DetailAdapter.ViewHolder> () {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val detailGrade: TextView = view.findViewById(R.id.detailGrade)
        val detailWeight: TextView = view.findViewById(R.id.detailWeight)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject_detail_line, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(grades[position]) {
            holder.detailGrade.text = "%.2f".format(grade)
            holder.detailWeight.text = "%.2f".format(weight)
        }
    }

    override fun getItemCount() = grades.size
}