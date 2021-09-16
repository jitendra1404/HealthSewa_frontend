package com.example.healthSewa.Entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Appointment (
    @ColumnInfo(name="_id") val _id:String? = null,
    @ColumnInfo(name="HealthIssue") val HealthIssue: String? = null,
    @ColumnInfo(name = "Occupation") val Occupation: String? = null,
    @ColumnInfo(name = "Behaviors") val Behaviors: String? = null,
    @ColumnInfo(name = "Date") val Date: String? = null,
    @ColumnInfo(name = "Age") val Age: String? = null,
    @ColumnInfo(name = "ConsultantHour") val ConsultantHour: String? = null,
    @ColumnInfo(name = "Statement") val Statement: String? = null,

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
        parcel.writeString(HealthIssue)
        parcel.writeString(Occupation)
        parcel.writeString(Behaviors)
        parcel.writeString(Date)
        parcel.writeString(Age)
        parcel.writeString(ConsultantHour)
        parcel.writeString(Statement)
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
