package com.bks.weatherapp.ui.weatherdetail

import androidx.lifecycle.LiveData
import com.bks.weather.models.WeatherInfo
import com.bks.weather.repository.WeatherRepository
import com.bks.weather.state.weather.WeatherDetailStateEvent
import com.bks.weather.state.weather.WeatherDetailStateEvent.GetCityWeather
import com.bks.weather.state.weather.WeatherDetailStateEvent.None
import com.bks.weather.state.weather.WeatherDetailViewState
import com.bks.weather.util.AbsentLiveData
import com.bks.weather.util.DataState
import com.bks.weatherapp.ui.BaseViewModel



class WeatherDetailViewModel
constructor(
    val weatherRepository: WeatherRepository
) : BaseViewModel<WeatherDetailStateEvent, WeatherDetailViewState>() {


    override fun handleStateEvent(stateEvent: WeatherDetailStateEvent): LiveData<DataState<WeatherDetailViewState>> {
        when(stateEvent) {
            is GetCityWeather -> {
                return weatherRepository.getWeatherByCityName(
                    stateEvent.cityName
                )
            }

            is None -> {
                return AbsentLiveData.createLiveData()
            }
        }
    }

    fun setWeatherData(weatherInfo : WeatherInfo?) {
        val update = getCurrentViewStateOrNew()
        update.weatherInfo = weatherInfo
        setViewState(update)
    }

    override fun initNewViewState(): WeatherDetailViewState {
        return WeatherDetailViewState()
    }

    fun cancelActiveJobs() {
        WeatherRepository.cancelActiveJobs()
    }

    override fun onCleared() {
        super.onCleared()
        cancelActiveJobs()
    }
}