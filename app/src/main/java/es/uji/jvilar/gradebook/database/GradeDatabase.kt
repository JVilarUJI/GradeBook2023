package es.uji.jvilar.gradebook.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subject::class, Grade::class],
    version = 1
)
private abstract class AbstractGradeDatabase: RoomDatabase() {
    abstract fun gradeDao(): GradeDao
}

class GradeDatabase private constructor(context: Context) {
    val dao = Room.databaseBuilder(context, AbstractGradeDatabase::class.java, "grades")
            .build()
            .gradeDao()

    companion object {
        @Volatile
        private var instance: GradeDatabase? = null

        fun getInstance(context: Context): GradeDatabase =
            instance ?: synchronized(this) {
                instance ?: GradeDatabase(context).also { instance = it }
            }
    }
}