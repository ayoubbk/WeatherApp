package com.bks.weather.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "weather_info")
data class WeatherInfo(

    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "city_id")
    var cityId: Int,

    @SerializedName(value = "name")
    @Expose
    @ColumnInfo(name = "city_name")
    var cityName: String,

    @SerializedName("cod")
    @Expose
    @ColumnInfo(name = "cod")
    var cod: String,

    @SerializedName("timezone")
    @Expose
    @ColumnInfo(name = "timezone")
    var timezone: Long,

    @SerializedName("base")
    @Expose
    @ColumnInfo(name = "base")
    var base: String,

    @SerializedName("dt")
    @Expose
    @ColumnInfo(name = "date")
    var date: Long,

    @SerializedName("visibility")
    @Expose
    @ColumnInfo(name = "visibility")
    var visibility: Long,

    @SerializedName("coord")
    @Expose
    @Embedded(prefix = "coord_")
    var coord : Coord,

    @SerializedName("weather")
    @Expose
    @Embedded(prefix = "weather_")
    var weather: ArrayList<Weather>,

    @SerializedName("main")
    @Expose
    @Embedded(prefix = "main_")
     var main : Main,

    @SerializedName("wind")
    @Expose
    @Embedded(prefix = "wind_")
    var wind : Wind,

    @SerializedName("clouds")
    @Expose
    @Embedded(prefix = "clouds_")
    var clouds : Clouds,

    @SerializedName("sys")
    @Expose
    @Embedded(prefix = "sys_")
    var sys: Sys

) : Parcelable

{

    override fun toString(): String {
        return "WeatherInfo(cityId=$cityId, cityName='$cityName', cod='$cod'," +
                " timezone=$timezone, base='$base', date=$date, visibility=$visibility," +
                " coord=$coord, weather=$weather, main=$main, wind=$wind, clouds=$clouds, sys=$sys)"
    }
}

@Parcelize
data class Coord(
    @ColumnInfo(name = "lon")
    var lon: Double,
    @ColumnInfo(name = "lat")
    var lat: Double
):Parcelable

@Parcelize
data class Weather(
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "main")
    var main: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "icon")
    var icon: String

): Parcelable

@Parcelize
data class Main(
    @ColumnInfo(name = "temp")
    var temp: Double,

    @ColumnInfo(name = "feels_like")
    var feels_like: Double,

    @ColumnInfo(name = "temp_min")
    var temp_min: Double,

    @ColumnInfo(name = "temp_max")
    var temp_max: Double,

    @ColumnInfo(name = "pressure")
    var pressure: Long,

    @ColumnInfo(name = "humidity")
    var humidity: Long

): Parcelable

@Parcelize
data class Wind(
    @ColumnInfo(name = "speed")
    var speed: Double,

    @ColumnInfo(name = "deg")
    var deg: Int

): Parcelable

@Parcelize
data class Clouds(
    @ColumnInfo(name = "all")
    var all: Int

): Parcelable

@Parcelize
data class Sys(

    @ColumnInfo(name = "type")
    var type: Int,

    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "country")
    var country: String,

    @ColumnInfo(name = "sunrise")
    var sunrise: Long,

    @ColumnInfo(name = "sunset")
    var sunset: Long

) : Parcelable

