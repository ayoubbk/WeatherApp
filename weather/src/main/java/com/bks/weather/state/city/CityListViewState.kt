package com.bks.weather.state.city

import com.bks.weather.models.City

data class CityListViewState(
    var cityList: ArrayList<City>? = null,
    var newCity: City? = null
) {

    class AddCityError {
        companion object {
            fun mustFillCityField(): String {
                return "City name required to add city"
            }
            fun none() : String {
                return "none"
            }
        }

    }
}