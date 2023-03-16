package es.uji.jvilar.gradebook

import es.uji.jvilar.gradebook.Model.Listener
import es.uji.jvilar.gradebook.database.Grade
import es.uji.jvilar.gradebook.database.Subject
import es.uji.jvilar.gradebook.database.SubjectGrade

class Presenter (private val model: Model, private val view: GradeView) {
    fun onNewSubjectRequested() = view.askForSubject()

    fun onNewSubjectAvailable(subject: Subject) =
        model.addSubject(subject, object: Listener<Unit> {
            override fun onResponse(data: Unit) {
                requestSubjectGrades()
            }
        })

    private fun requestSubjectGrades() {
        model.getSubjectGrades(object : Listener<List<SubjectGrade>> {
            override fun onResponse(data: List<SubjectGrade>) {
                view.showSubjectGrades(data)
            }
        })
    }

    fun onNewGradeRequested() {
        model.getSubjects(object : Listener<List<Subject>> {
            override fun onResponse(data: List<Subject>) {
                view.askForGrade(data)
            }
        })
    }

    fun onNewGradeAvailable(grade: Grade) =
        model.addGrade(grade, object : Listener<Unit> {
            override fun onResponse(data: Unit) {
                requestSubjectGrades()
            }
        })

    init {
        requestSubjectGrades()
    }
}
