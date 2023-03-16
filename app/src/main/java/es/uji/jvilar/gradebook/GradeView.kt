package es.uji.jvilar.gradebook

import es.uji.jvilar.gradebook.database.Subject

interface GradeView {
    fun askForSubject()
    fun showSubjects(subjects: List<Subject>)
}
