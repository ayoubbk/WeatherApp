package com.bks.weather.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bks.weather.models.City
import com.bks.weather.models.WeatherInfo


@Database(entities = [City::class, WeatherInfo::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getCityDao(): CityDao

    abstract fun getWeatherInfoDao(): WeatherInfoDao

    companion object{
        const val DATABASE_NAME: String = "app_db"

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration() // get correct db version if schema changed
                .build()
        }
    }


}