package com.bks.weather.state.weather


sealed class WeatherDetailStateEvent {

    data class GetCityWeather(val cityName : String): WeatherDetailStateEvent()

    object None : WeatherDetailStateEvent()

}