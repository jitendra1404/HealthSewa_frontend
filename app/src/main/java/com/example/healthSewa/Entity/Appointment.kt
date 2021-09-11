package com.example.healthSewa.Entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Appointment (
    @ColumnInfo(name="_id") val _id:String? = null,
    @ColumnInfo(name="weight") val weight: String? = null,
    @ColumnInfo(name = "age") val age: String? = null,
    @ColumnInfo(name = "date") val date: String? = null,
    @ColumnInfo(name = "healthissue") val healthissue: String? = null,
    @ColumnInfo(name = "status") val status: String? = null,
    @ColumnInfo(name = "occupation") val occupation: String? = null,
    @ColumnInfo(name = "statement") val statement: String? = null,
    @ColumnInfo(name = "gender") val sex: String? = null,
) :Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id") var id:Int=0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(weight)
        parcel.writeString(age)
        parcel.writeString(date)
        parcel.writeString(healthissue)
        parcel.writeString(status)
        parcel.writeString(occupation)
        parcel.writeString(statement)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Appointment> {
        override fun createFromParcel(parcel: Parcel): Appointment {
            return Appointment(parcel)
        }

        override fun newArray(size: Int): Array<Appointment?> {
            return arrayOfNulls(size)
        }
    }
}
