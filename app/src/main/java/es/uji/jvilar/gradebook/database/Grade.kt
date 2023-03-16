package es.uji.jvilar.gradebook.database

import androidx.room.*

@Entity(foreignKeys = [
    ForeignKey (entity = Subject::class,
        parentColumns = ["code"],
        childColumns = ["subject_code"])],
    indices = [Index(value= ["subject_code"])])
data class Grade(
    @ColumnInfo(name = "subject_code")
    val subjectCode: String,
    val weight: Double,
    val grade: Double) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}