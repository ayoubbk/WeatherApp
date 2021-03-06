package com.bks.weatherapp.viewmodels

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.bks.weather.repository.WeatherRepository
import com.bks.weatherapp.ui.weatherdetail.WeatherDetailViewModel


@Suppress("UNCHECKED_CAST")
class WeatherViewModelFactory constructor(
    private val weatherRepository: WeatherRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {

            isAssignableFrom(WeatherDetailViewModel::class.java) ->
                WeatherDetailViewModel(weatherRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
