package com.bks.weatherapp.ui.weatherdetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bks.weather.models.WeatherInfo
import com.bks.weather.state.weather.WeatherDetailStateEvent
import com.bks.weatherapp.R
import com.bks.weatherapp.util.DateUtil
import com.bks.weatherapp.util.getWeatherViewModelFactory
import kotlinx.android.synthetic.main.fragment_weather_detail.*
import kotlin.math.roundToInt

private const val TAG = "WeatherDetailFragment"
const val CITY_DETAIL_SELECTED_BUNDLE_KEY = "selectedCity"

class WeatherDetailFragment : Fragment(R.layout.fragment_weather_detail) {


    private val viewModel by viewModels<WeatherDetailViewModel> { getWeatherViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        triggerGetWeatherEvent()
        subscribeObserver()
    }

    private fun subscribeObserver() {

        viewModel.dataState.observe(viewLifecycleOwner, Observer{ dataState ->
            if(dataState != null){
                dataState.data?.let { data ->
                    data.data?.let{ event ->
                        event.getContentIfNotHandled()?.let{ viewState ->
                            viewState.weatherInfo?.let{ weatherInfo ->
                                Log.d(TAG, "WeatherDetailFragment, DataState: $weatherInfo")
                                viewModel.setWeatherData(weatherInfo)
                            }
                        }
                    }
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            if(viewState != null) {
                viewState.weatherInfo?.let { weatherInfo ->
                    setWeatherInfo(weatherInfo)
                }
            }
        })
    }

    private fun triggerGetWeatherEvent() {
        arguments.let {
                it?.get(CITY_DETAIL_SELECTED_BUNDLE_KEY).toString().let {city ->
                    Log.d(TAG, "triggerGetWeatherEvent: city name : $city")
                    viewModel.setStateEvent(WeatherDetailStateEvent.GetCityWeather(cityName = city))
            }
        }
    }

    private fun setWeatherInfo(weatherInfo: WeatherInfo) {
        // set widget
        Log.d(TAG, "SET WEATHER PROPERTIES : $weatherInfo")

        tv_city_name.text = weatherInfo.cityName
        tv_date.text = DateUtil.convertTimestampToStringData(weatherInfo.date)
        tv_status.text = weatherInfo.weather[0].description

        tv_temp.text = weatherInfo.main.temp.roundToInt().toString().plus(" °C")
        tv_temp_min.text = weatherInfo.main.temp_min.toString().plus(" °C")
        tv_temp_max.text = weatherInfo.main.temp_max.toString().plus(" °C")
        tv_sunrise.text = weatherInfo.sys.sunrise.toString() // TODO Format date
        tv_sunset.text = weatherInfo.sys.sunset.toString() // TODO Format date
        tv_wind.text = weatherInfo.wind.speed.toString()
        tv_pressure.text = weatherInfo.main.pressure.toString()
        tv_humidity.text = weatherInfo.main.humidity.toString()
    }

}