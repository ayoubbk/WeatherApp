package com.bks.weather.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.bks.weather.api.RetrofitBuilder
import com.bks.weather.models.WeatherInfo
import com.bks.weather.persistence.WeatherInfoDao
import com.bks.weather.state.weather.WeatherDetailViewState
import com.bks.weather.util.*
import kotlinx.coroutines.Job

class WeatherRepository
constructor(val weatherInfoDao: WeatherInfoDao)
{


    fun getWeatherByCityName(cityName : String): LiveData<DataState<WeatherDetailViewState>> {

        return object : NetworkBoundResource<WeatherInfo, WeatherInfo, WeatherDetailViewState>(
            true,
            true,
            false
        ) {

            // to be implemented when handle weather cache
            override suspend fun createCacheRequestAndReturn() {

            }

            override suspend fun handleApiSuccessResponse(response: GenericApiResponse.ApiSuccessResponse<WeatherInfo>) {
                Log.d(TAG, "handleApiSuccessResponse: $response")
                onCompleteJob(
                    DataState.data(
                        data = WeatherDetailViewState(
                            weatherInfo = response.body
                        )
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<WeatherInfo>> {
                return RetrofitBuilder.apiService.getWeatherByCityName(cityName, Constants.UNITS, Constants.API_KEY)
            }

            // to be implemented when handle weather cache
            override fun loadFromCache(): LiveData<WeatherDetailViewState> {
                return AbsentLiveData.createLiveData()
            }

            // to be implemented when handle weather cache
            override suspend fun updateLocalDb(cacheObject: WeatherInfo?) {

            }

            override fun setJob(job: Job) {
                repositoryJob?.cancel()
                repositoryJob = job
            }
        }.asLiveData()
    }



    companion object {
        private const val TAG: String = "AppDebug"

        private var repositoryJob: Job? = null

        fun cancelActiveJobs() {
            Log.d(TAG, "WeatherRepository: Cancelling on-going jobs")
            repositoryJob?.cancel()
        }

        @Volatile private var instance: WeatherRepository? = null

        fun getInstance(weatherInfoDao: WeatherInfoDao) =
            instance ?: synchronized(this) {
                instance ?: WeatherRepository(weatherInfoDao).also { instance = it }
            }
    }
}