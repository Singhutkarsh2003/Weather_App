package com.example.weatherapp.data.remote.api

import com.example.weatherapp.data.remote.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi{


    @GET("v1/forecast")
    suspend fun  getWeather(

        @Query("latitude")
        latitude : Double,

        @Query("longitude")
        longitude : Double,

        @Query("current")
        current: String = "temperature_2m,relative_humidity_2m,pressure_msl,wind_speed_10m"

    ): WeatherResponse

}