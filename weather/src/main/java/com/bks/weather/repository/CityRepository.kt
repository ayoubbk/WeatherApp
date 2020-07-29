package com.bks.weather.repository

import androidx.lifecycle.LiveData
import com.bks.weather.models.City
import com.bks.weather.persistence.CityDao
import com.bks.weather.state.city.CityListViewState
import com.bks.weather.util.DataState
import com.bks.weather.util.GenericApiResponse
import com.bks.weather.util.NetworkBoundResource
import kotlinx.coroutines.Job

class CityRepository
constructor(val cityDao: CityDao)
{


    fun insertCity(city: City): LiveData<DataState<CityListViewState>> {
        return object : NetworkBoundResource<City, City, CityListViewState>(
            true,
            false,
            true
        ) {

            override suspend fun createCacheRequestAndReturn() {
                TODO("Not yet implemented")
            }

            override suspend fun handleApiSuccessResponse(response: GenericApiResponse.ApiSuccessResponse<City>) {
                TODO("Not yet implemented")
            }

            override fun createCall(): LiveData<GenericApiResponse<City>> {
                TODO("Not yet implemented")
            }

            override fun loadFromCache(): LiveData<CityListViewState> {
                TODO("Not yet implemented")
            }

            override suspend fun updateLocalDb(cacheObject: City?) {
                TODO("Not yet implemented")
            }

            override fun setJob(job: Job) {
                TODO("Not yet implemented")
            }
        }.asLiveData()
    }

    fun getAllCities(): LiveData<DataState<CityListViewState>> {
        return object : NetworkBoundResource<City, City, CityListViewState>(
            true,
            false,
            true
        ) {

            override suspend fun createCacheRequestAndReturn() {
                TODO("Not yet implemented")
            }

            override suspend fun handleApiSuccessResponse(response: GenericApiResponse.ApiSuccessResponse<City>) {
                TODO("Not yet implemented")
            }

            override fun createCall(): LiveData<GenericApiResponse<City>> {
                TODO("Not yet implemented")
            }

            override fun loadFromCache(): LiveData<CityListViewState> {
                TODO("Not yet implemented")
            }

            override suspend fun updateLocalDb(cacheObject: City?) {
                TODO("Not yet implemented")
            }

            override fun setJob(job: Job) {
                TODO("Not yet implemented")
            }
        }.asLiveData()
    }

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: CityRepository? = null

        fun getInstance(cityDao: CityDao) =
            instance ?: synchronized(this) {
                instance ?: CityRepository(cityDao).also { instance = it }
            }
    }
}