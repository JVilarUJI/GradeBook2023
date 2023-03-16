package es.uji.jvilar.gradebook

import es.uji.jvilar.gradebook.Model.Listener
import es.uji.jvilar.gradebook.database.Subject

class Presenter (private val model: Model, private val view: GradeView) {
    fun onNewSubjectRequested() = view.askForSubject()

    fun onNewSubjectAvailable(subject: Subject) =
        model.addSubject(subject, object: Listener<Unit> {
            override fun onResponse(data: Unit) {
                requestSubjects()
            }
        })

    private fun requestSubjects() {
        model.getSubjects(object : Listener<List<Subject>> {
            override fun onResponse(data: List<Subject>) {
                view.showSubjects(data)
            }
        })
    }

    init {
        requestSubjects()
    }
}
