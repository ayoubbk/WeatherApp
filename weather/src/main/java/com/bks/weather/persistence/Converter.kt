package com.bks.weather.persistence

import androidx.room.TypeConverter
import com.bks.weather.models.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converter {

    @TypeConverter
    fun weatherListToString(list: ArrayList<Weather>): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToWeatherList(value: String): ArrayList<Weather> {
        val listType = object : TypeToken<ArrayList<Weather>>() {}.type
        return Gson().fromJson(value, listType)
    }
}