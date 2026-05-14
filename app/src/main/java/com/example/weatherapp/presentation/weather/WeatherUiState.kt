package com.example.weatherapp.presentation.weather

import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.Weather

data class WeatherUiState(

    val isLoading : Boolean = false,
    val cities : List<City> = emptyList(),
    val weather : Weather? = null,
    val error : String? = null,
    )