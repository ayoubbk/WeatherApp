package com.bks.weatherapp.util

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {


    companion object {
        private val sdf = SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.FRENCH)

        fun convertTimestampToStringData(timestamp: Long): String{
            val stamp = Timestamp(timestamp)
            val date = Date(stamp.time)
            return sdf.format(date)


        }


    }
}
