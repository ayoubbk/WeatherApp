package com.bks.weatherapp.ui.citylist

import android.os.Parcelable
import androidx.lifecycle.LiveData
import com.bks.weather.models.City
import com.bks.weather.repository.CityRepository
import com.bks.weather.state.city.CityListStateEvent
import com.bks.weather.state.city.CityListViewState
import com.bks.weather.util.AbsentLiveData
import com.bks.weather.util.DataState
import com.bks.weatherapp.ui.BaseViewModel
import java.util.*


class CityListViewModel
constructor(
    val cityRepository: CityRepository
) : BaseViewModel<CityListStateEvent, CityListViewState>() {


    init {
        setStateEvent(CityListStateEvent.GetAllCitiesEvent)
    }

    override fun handleStateEvent(stateEvent: CityListStateEvent): LiveData<DataState<CityListViewState>> {
         return when (stateEvent) {
            is CityListStateEvent.AddCityEvent -> {
                cityRepository.insertCity(
                    City(
                        id = UUID.randomUUID().toString(),
                        name = stateEvent.cityName
                    )
                )
            }

            is CityListStateEvent.GetAllCitiesEvent -> {
                cityRepository.getAllCities()
            }

            is CityListStateEvent.None -> {
                AbsentLiveData.createLiveData()
            }
        }
    }

    fun setCityListData(cityList : ArrayList<City>) {
        val update = getCurrentViewStateOrNew()
        update.cityList = cityList
        setViewState(update)
    }

    fun setCityData(city : City?) {
        val update = getCurrentViewStateOrNew()
        update.newCity = city
        setViewState(update)
    }

    override fun initNewViewState(): CityListViewState {
        return CityListViewState()
    }

    fun clearList(){
        val update = getCurrentViewStateOrNew()
        update.cityList = ArrayList()
        setViewState(update)
    }

    fun setLayoutManagerState(layoutManagerState: Parcelable){
        val update = getCurrentViewStateOrNew()
        update.layoutManagerState = layoutManagerState
        setViewState(update)
    }

    private fun cancelActiveJobs() {
        CityRepository.cancelActiveJobs()
    }

    override fun onCleared() {
        super.onCleared()
        cancelActiveJobs()
    }


}