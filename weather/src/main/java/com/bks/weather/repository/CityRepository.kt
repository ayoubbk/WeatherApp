package com.bks.weather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import com.bks.weather.models.City
import com.bks.weather.persistence.CityDao
import com.bks.weather.state.city.CityListViewState
import com.bks.weather.util.AbsentLiveData
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
            false
        ) {

            override suspend fun createCacheRequestAndReturn() {
                cityDao.insertCity(city)
            }

            override suspend fun handleApiSuccessResponse(response: GenericApiResponse.ApiSuccessResponse<City>) {
                TODO("Not yet implemented")
            }

            override fun createCall(): LiveData<GenericApiResponse<City>> {
                TODO("Not yet implemented")
            }

            override fun loadFromCache(): LiveData<CityListViewState> {
                return cityDao.getCity(city.name)
                    .switchMap {
                        object: LiveData<CityListViewState>(){
                            override fun onActive() {
                                super.onActive()
                                value = CityListViewState(
                                    newCity = it
                                )
                            }
                        }
                    }
            }

            override suspend fun updateLocalDb(cacheObject: City?) {

            }

            override fun setJob(job: Job) {
                repositoryJob?.cancel()
                repositoryJob = job
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
                // Ignore
            }

            override suspend fun handleApiSuccessResponse(response: GenericApiResponse.ApiSuccessResponse<City>) {
                // Ignore
            }

            override fun createCall(): LiveData<GenericApiResponse<City>> {
                return AbsentLiveData.createLiveData()
            }

            override fun loadFromCache(): LiveData<CityListViewState> {
                return cityDao.getAllCities()
                    .switchMap {
                        object: LiveData<CityListViewState>(){
                            override fun onActive() {
                                super.onActive()
                                value = CityListViewState(
                                    cityList = it as ArrayList<City>
                                )
                            }
                        }
                    }
            }

            override suspend fun updateLocalDb(cacheObject: City?) {
                TODO("Not yet implemented")
            }

            override fun setJob(job: Job) {
                repositoryJob?.cancel()
                repositoryJob = job
            }
        }.asLiveData()
    }

    companion object {
        private var repositoryJob: Job? = null

        fun cancelActiveJobs() {
            repositoryJob?.cancel()
        }

        @Volatile private var instance: CityRepository? = null

        fun getInstance(cityDao: CityDao) =
            instance ?: synchronized(this) {
                instance ?: CityRepository(cityDao).also { instance = it }
            }
    }
}