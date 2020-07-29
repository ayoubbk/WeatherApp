package com.bks.weather.util

import android.content.Context
import com.bks.weather.persistence.AppDatabase
import com.bks.weather.repository.CityRepository
import com.bks.weather.repository.WeatherRepository

object WeatherServiceLocator  {

    @Volatile
    var cityRepository: CityRepository? = null
    @Volatile
    var weatherRepository: WeatherRepository? = null

    fun provideCityRepository(context: Context): CityRepository {
        synchronized(this) {
            return cityRepository ?: cityRepository ?: createCityRepository(context)
        }
    }

    fun provideWeatherInfoRepository(context: Context): WeatherRepository {
        synchronized(this) {
            return weatherRepository ?: weatherRepository ?: createWeatherRepository(context)
        }
    }

    private fun createCityRepository(context: Context): CityRepository {
        return CityRepository.getInstance(AppDatabase.getInstance(context.applicationContext).getCityDao())
    }

    private fun createWeatherRepository(context: Context): WeatherRepository {
        return WeatherRepository.getInstance(AppDatabase.getInstance(context.applicationContext).getWeatherInfoDao())
    }


}