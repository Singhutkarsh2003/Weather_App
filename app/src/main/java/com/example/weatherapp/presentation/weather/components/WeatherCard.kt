package com.example.weatherapp.presentation.weather.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weatherapp.domain.model.Weather

@Composable
fun WeatherCard(

    weather: Weather

){

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {

            Text(text = weather.cityName)

            Text(text = "Temperature: ${weather.temperature}°C")

            Text(text = "Humidity: ${weather.humidity}%")

            Text(text = "Pressure: ${weather.pressure}")

            Text(text = "Wind Speed: ${weather.windSpeed}")

        }

    }

}