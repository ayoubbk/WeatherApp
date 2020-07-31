package com.bks.weatherapp.util

import androidx.fragment.app.Fragment
import com.bks.weatherapp.WeatherApplication
import com.bks.weatherapp.viewmodels.CityViewModelFactory
import com.bks.weatherapp.viewmodels.WeatherViewModelFactory

fun Fragment.getCityViewModelFactory(): CityViewModelFactory {
    val repository = (requireContext().applicationContext as WeatherApplication).cityRepository
    return CityViewModelFactory(repository, this)
}

fun Fragment.getWeatherViewModelFactory(): WeatherViewModelFactory {
    val repository = (requireContext().applicationContext as WeatherApplication).weatherRepository
    return WeatherViewModelFactory(repository, this)
}

fun <T> List<T>.toArrayList(): ArrayList<T>{
    return ArrayList(this)
}