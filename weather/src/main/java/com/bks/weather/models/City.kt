package com.bks.weather.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "city")
data class City(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
): Parcelable

{
    override fun toString(): String {
        return "City(id=$id, name='$name')"
    }
}