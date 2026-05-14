package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.model.WeatherReport
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun searchCities(
        query: String
    ):List <City>

    suspend fun getWeather(
        latitude : Double,
        longitude: Double,
        cityName: String
    ): Weather

    suspend fun saveReport(
        report: WeatherReport
    )

    fun getReports(): Flow<List<WeatherReport>>

}