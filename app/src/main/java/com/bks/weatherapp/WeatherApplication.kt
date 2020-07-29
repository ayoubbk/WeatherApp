package com.bks.weatherapp

import android.app.Application
import com.bks.weather.repository.CityRepository
import com.bks.weather.repository.WeatherRepository
import com.bks.weather.util.WeatherServiceLocator

class WeatherApplication : Application() {


    val cityRepository: CityRepository
        get() = WeatherServiceLocator.provideCityRepository(this)

    val weatherRepository: WeatherRepository
        get() = WeatherServiceLocator.provideWeatherInfoRepository(this)


    override fun onCreate() {
        super.onCreate()

    }
}