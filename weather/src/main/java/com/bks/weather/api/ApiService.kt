package com.bks.weather.api

import androidx.lifecycle.LiveData
import com.bks.weather.models.WeatherInfo
import com.bks.weather.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/weather")
    fun getWeatherByCityName(
        @Query("q") cityName : String,
        @Query("units") unit : String,
        @Query("appid") appId : String
    ): LiveData<GenericApiResponse<WeatherInfo>>
}