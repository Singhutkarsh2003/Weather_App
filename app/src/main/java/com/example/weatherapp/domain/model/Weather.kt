package com.example.weatherapp.domain.model

data class Weather(

    val cityName : String,
    val temperature : Double,
    val humidity : Int,
    val pressure : Double,
    val windSpeed : Double,
    val condition: String

)
