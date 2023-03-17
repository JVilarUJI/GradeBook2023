package es.uji.jvilar.gradebook

import android.content.Context
import es.uji.jvilar.gradebook.database.Grade
import es.uji.jvilar.gradebook.database.GradeDatabase
import es.uji.jvilar.gradebook.database.Subject
import es.uji.jvilar.gradebook.database.SubjectGrade
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Model (context: Context){
    interface Listener<Data> {
        fun onResponse(data: Data)
    }

    private val dao = GradeDatabase.getInstance(context).dao

    fun addSubject(subject: Subject, listener: Listener<Unit>) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                dao.insertSubject(subject)
            }
            listener.onResponse(Unit)
        }
    }

    fun addGrade(grade: Grade, listener: Listener<Unit>) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                dao.insertGrade(grade)
            }
            listener.onResponse(Unit)
        }
    }

    fun getSubjects(listener: Listener<List<Subject>>) {
        CoroutineScope(Dispatchers.Main).launch {
            val grades = withContext(Dispatchers.IO) {
                dao.getSubjects()
            }
            listener.onResponse(grades)
        }
    }

    fun getSubjectGrades(listener: Listener<List<SubjectGrade>>) {
        CoroutineScope(Dispatchers.Main).launch {
            val subjectGrades = withContext(Dispatchers.IO) {
                dao.getSubjectGrades()
            }
            listener.onResponse(subjectGrades)
        }
    }

    fun getGrades(subject: Subject, listener: Listener<List<Grade>>) {
        CoroutineScope(Dispatchers.Main).launch  {
            val subjectGrades = withContext(Dispatchers.IO) {
                dao.getGrades(subject.code)
            }
            listener.onResponse(subjectGrades)
        }
    }
}
