package es.uji.jvilar.gradebook.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject (
    @PrimaryKey
    val code: String,
    val name: String)