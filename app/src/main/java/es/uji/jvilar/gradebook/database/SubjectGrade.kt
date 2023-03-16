package es.uji.jvilar.gradebook.database

import androidx.room.Embedded

data class SubjectGrade (
    @Embedded
    val subject: Subject,
    val gradeSum: Double,
    val weightSum: Double)
