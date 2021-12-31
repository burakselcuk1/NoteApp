package me.ibrahimsn.smoothbottombar.ui.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "note_table")
data class notes  (

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var head: String,
    var desciription: String
):Parcelable