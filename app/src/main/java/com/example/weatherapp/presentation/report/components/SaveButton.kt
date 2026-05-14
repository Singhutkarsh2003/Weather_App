package com.example.weatherapp.presentation.report.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SaveButton(
    onClick : () -> Unit
){

    Button(
        onClick = onClick
    ) {

        Text(text = "Save Report")

    }
}