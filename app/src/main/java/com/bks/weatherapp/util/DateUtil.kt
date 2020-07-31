package com.bks.weatherapp.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    companion object {

        private const val TAG = "DateUtil"

        fun convertTimestampToStringDate(timestamp: Long): String {
            var date = ""
            try {
                date = SimpleDateFormat("EEE, MMM d, yyyy HH:mm", Locale.getDefault()).format(
                    Date(timestamp * 1000L))
            } catch (e: Exception) {
                Log.e(TAG, "Exception in Date formatting: " + e.message)
            }
            return date
        }

        fun convertTimestampToTime(timestamp: Long): String {
            var date = ""
            try {
                date = SimpleDateFormat("HH:mm", Locale.getDefault()).format(
                    Date(timestamp * 1000L))
            } catch (e: Exception) {
                Log.e(TAG, "Exception in Date formatting: " + e.message)
            }
            return date
        }
    }
}
