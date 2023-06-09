package es.uji.jvilar.gradebook

import es.uji.jvilar.gradebook.database.Grade
import es.uji.jvilar.gradebook.database.Subject
import es.uji.jvilar.gradebook.database.SubjectGrade

interface GradeView {
    fun askForSubject()
    fun showSubjectGrades(subjectGrades: List<SubjectGrade>)
    fun askForGrade(subjects: List<Subject>)
    fun showSubjectDetails(subject: Subject, grades: List<Grade>)
}
