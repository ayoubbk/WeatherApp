package com.bks.weather.state.city



sealed class CityListStateEvent {

    data class AddCityEvent(val cityName : String): CityListStateEvent()

    object GetAllCitiesEvent : CityListStateEvent()

    object None : CityListStateEvent()

}