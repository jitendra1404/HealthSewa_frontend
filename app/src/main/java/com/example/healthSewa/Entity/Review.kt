package com.example.healthSewa.Entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review (

@ColumnInfo(name="_id") val _id:String? = null,
@ColumnInfo(name="Report_title") val Report_title: String? = null,
@ColumnInfo(name = "Report_description") val Report_description: String? = null,
@ColumnInfo(name = "Specialist_name") val  Specialist_name: String? = null,

) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id") var id:Int=0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(Report_title)
        parcel.writeString(Report_description)
        parcel.writeString(Specialist_name)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }
}
