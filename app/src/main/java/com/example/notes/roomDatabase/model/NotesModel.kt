package com.example.notes.roomDatabase.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NotesModel() : Parcelable{
    @PrimaryKey(autoGenerate = true)
    var id = 0
    @ColumnInfo(name = "title")
    var title : String? = ""
    @ColumnInfo(name = "description")
    var description : String? = ""
    @ColumnInfo(name = "date")
    var date : String = ""
    @ColumnInfo(name = "pinned")
    var pinned : Boolean = false

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        title = parcel.readString()
        description = parcel.readString()
        date = parcel.readString().toString()
        pinned = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(date)
        parcel.writeByte(if (pinned) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NotesModel> {
        override fun createFromParcel(parcel: Parcel): NotesModel {
            return NotesModel(parcel)
        }

        override fun newArray(size: Int): Array<NotesModel?> {
            return arrayOfNulls(size)
        }
    }

}