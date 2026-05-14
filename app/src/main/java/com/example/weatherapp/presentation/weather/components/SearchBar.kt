package com.example.weatherapp.presentation.weather.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchBar(

    query : String,
    onQueryChange : (String) -> Unit,
    modifier: Modifier = Modifier

){

    OutlinedTextField(

        value = query,
        onValueChange = onQueryChange,
        modifier = modifier,
        label = { Text("Search City") }
    )
}