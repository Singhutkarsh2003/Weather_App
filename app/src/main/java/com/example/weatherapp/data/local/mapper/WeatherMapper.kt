package com.example.weatherapp.data.local.mapper

import com.example.weatherapp.data.remote.dto.CurrentWeatherDto
import com.example.weatherapp.domain.model.Weather

fun CurrentWeatherDto.toWeather(
    cityName : String
): Weather{

    return Weather(

        cityName = cityName,

        temperature = temperature ?: 0.0,

        humidity = humidity ?: 0,

        pressure = pressure ?: 0.0,

        windSpeed = windSpeed ?: 0.0,

        condition = "Clear"

    )


}