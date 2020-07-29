package com.bks.weather.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bks.weather.models.WeatherInfo

@Dao
interface WeatherInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherInfo(weatherInfo: WeatherInfo): Long

    @Query("SELECT * FROM weather_info")
    fun getWeatherInfo(): LiveData<WeatherInfo>
}