package es.uji.jvilar.gradebook.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import java.util.*

@Entity(foreignKeys = [
    ForeignKey (entity = Subject::class,
        parentColumns = ["code"],
        childColumns = ["subject_code"])],
    indices = [Index(value= ["subject_code"])])
data class Grade(
    @ColumnInfo(name = "subject_code")
    val subjectCode: String,
    val weight: Double,
    val grade: Double) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble()
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(subjectCode)
        parcel.writeDouble(weight)
        parcel.writeDouble(grade)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Grade> {
        override fun createFromParcel(parcel: Parcel): Grade {
            return Grade(parcel)
        }

        override fun newArray(size: Int): Array<Grade?> {
            return arrayOfNulls(size)
        }
    }
}