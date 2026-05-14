package com.example.weatherapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

    @SerializedName("current")
    val current : CurrentWeatherDto?

)