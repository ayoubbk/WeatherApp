package com.bks.weather.util

class Constants {

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/"
        const val API_KEY = "c4bbe062a2d0d719517a428e3d78538c"
        const val UNITS = "metric" // to get Temp in C°

        const val NETWORK_TIMEOUT = 6000L
        const val TESTING_NETWORK_DELAY = 0L // fake network delay for testing
        const val TESTING_CACHE_DELAY = 0L // fake cache delay for testing
    }
}