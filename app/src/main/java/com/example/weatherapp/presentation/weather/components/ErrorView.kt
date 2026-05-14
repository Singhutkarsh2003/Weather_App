package com.example.weatherapp.presentation.weather.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorView(
    message: String
){
    Text(text = message)
}