package com.example.weatherapp.presentation.weather.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weatherapp.domain.model.City

@Composable
fun SuggestionItem(

    city: City,
    onClick : () -> Unit
){

    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable{
            onClick()
        }
    ) {

        Column{

            Text(text = city.name)
            Text(text = "${city.state}, ${city.country}")
        }

    }

}