package com.example.weatherapp.data.remote.repository

import com.example.weatherapp.data.local.dao.ReportDao
import com.example.weatherapp.data.local.entity.WeatherReportEntity
import com.example.weatherapp.data.local.mapper.toCity
import com.example.weatherapp.data.local.mapper.toWeather
import com.example.weatherapp.data.remote.api.GeocodingApi
import com.example.weatherapp.data.remote.api.WeatherApi
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.model.WeatherReport
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(

    private val weatherApi: WeatherApi,
    private  val  geocodingApi : GeocodingApi,
    private val  reportDao : ReportDao
): WeatherRepository{

    override suspend fun searchCities(query: String): List<City> {
        return geocodingApi.searchCity(query).results?.map { it.toCity() }?: emptyList()
    }

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double,
        cityName: String
    ): Weather {
        val response = weatherApi.getWeather(latitude, longitude)

        return response.current!!.toWeather(cityName)
    }

    override suspend fun saveReport(report: WeatherReport) {
        reportDao.insertReport(
            WeatherReportEntity(
                cityName = report.cityName,
                temperature = report.temperature,
                humidity = report.humidity,
                pressure = report.pressure,
                windSpeed = report.windSpeed,
                condition = report.condition,
                notes = report.notes,
                imagePath = report.imagePath,
                originalImageSize = report.originalImageSize,
                compressedImageSize = report.compressedImageSize,
                timestamp = report.timestamp

            )
        )
    }

    override fun getReports(): Flow<List<WeatherReport>> {
        return reportDao.getReports().map {
            it.map {entity ->
                WeatherReport(

                    id = entity.id,
                    cityName = entity.cityName,
                    temperature = entity.temperature,
                    humidity = entity.humidity,
                    pressure = entity.pressure,
                    windSpeed = entity.windSpeed,
                    condition = entity.condition,
                    notes = entity.notes,
                    imagePath = entity.imagePath,
                    originalImageSize = entity.originalImageSize,
                    compressedImageSize = entity.compressedImageSize,
                    timestamp = entity.timestamp
                )
            }
        }
    }

}