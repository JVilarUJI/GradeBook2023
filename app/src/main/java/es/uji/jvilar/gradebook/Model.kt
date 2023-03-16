package es.uji.jvilar.gradebook

import android.content.Context
import es.uji.jvilar.gradebook.database.GradeDatabase

class Model (context: Context){
    private var dao = GradeDatabase.getInstance(context).dao

}
