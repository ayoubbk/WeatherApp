package com.bks.weather.state.city

import android.os.Parcelable
import com.bks.weather.models.City
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityListViewState(
    var cityList: ArrayList<City> = ArrayList<City>(),
    var newCity: City? = null,
    var layoutManagerState: Parcelable? = null
): Parcelable {

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