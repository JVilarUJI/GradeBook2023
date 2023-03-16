package es.uji.jvilar.gradebook.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GradeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubject(subject: Subject)

    @Insert
    fun insertGrade(grade: Grade)

    @Query("SELECT * FROM subject")
    fun getSubjects(): List<Subject>

    @Query("SELECT code, name, SUM(weight * grade) AS gradeSum, SUM (weight) AS weightSum" +
            " FROM subject LEFT JOIN grade on subject_code == code" +
            " GROUP BY code" +
            " ORDER BY code")
    fun getSubjectGrades(): List<SubjectGrade>
}