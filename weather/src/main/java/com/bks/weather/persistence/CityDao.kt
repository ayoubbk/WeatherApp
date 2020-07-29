package com.bks.weather.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bks.weather.models.City

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: City): Long

    @Query("SELECT * FROM city")
    fun getAllCities(): LiveData<List<City>>

    @Query("SELECT * FROM city WHERE name = :cityName")
    fun getCity(cityName : String) : LiveData<City>


}