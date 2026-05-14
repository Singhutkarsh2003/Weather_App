package com.example.weatherapp.domain.model

data class WeatherReport(

    val id : Int =0,
    val cityName : String,
    val temperature : Double,
    val humidity : Int,
    val pressure : Double,
    val windSpeed : Double,
    val condition : String,
    val notes : String,
    val imagePath : String,
    val originalImageSize : Long,
    val compressedImageSize : Long,
    val timestamp : Long
)
