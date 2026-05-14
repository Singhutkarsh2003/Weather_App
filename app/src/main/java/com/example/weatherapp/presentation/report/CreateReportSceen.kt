package com.example.weatherapp.presentation.report

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.domain.model.WeatherReport
import com.example.weatherapp.presentation.navigation.Routes
import com.example.weatherapp.presentation.report.components.ImagePreview
import com.example.weatherapp.presentation.report.components.NotesTextField
import com.example.weatherapp.presentation.report.components.SaveButton
import com.example.weatherapp.presentation.utils.ImageCompressor
import com.example.weatherapp.presentation.utils.ImageStorage
import java.io.File


@Composable
fun CreateReportScreen(
    navController: NavController,
    city: String,
    temperature: String,
    humidity: String,
    pressure: String,
    windSpeed: String,
    viewModel: ReportViewModel = hiltViewModel()

) {

    val context = LocalContext.current

    var notes by remember { mutableStateOf("") }
    var imagePath by remember { mutableStateOf(ImageStorage.latestCapturedImage) }

    LaunchedEffect(
        ImageStorage.latestCapturedImage
    ) {
        imagePath = ImageStorage.latestCapturedImage
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        Text(
            text = "Create Report",
            style = MaterialTheme.typography.headlineMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth()

        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = city
                )

                Text(
                    text = "Temperature: $temperature °C"
                )

                Text(
                    text = "Humidity: $humidity %"
                )

                Text(
                    text = "Pressure: $pressure hPa"
                )

                Text(
                    text = "Wind Speed: $windSpeed km/h"
                )
            }
        }
        ImagePreview(
            imagePath = imagePath
        )

        Button(
            onClick = {
                navController.navigate(Routes.CAMERA)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Capture Photo")
        }

        NotesTextField(
            notes = notes,
            onNotesChange = {
                notes = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        SaveButton(

            onClick = {

                val compressedResult =

                    if (!imagePath.isNullOrBlank()) {

                        ImageCompressor.compressImage(

                            context = context,

                            imagePath = imagePath!!
                        )

                    } else {

                        Pair("", 0L)
                    }

                val report = WeatherReport(

                    id = 0,

                    cityName = city,

                    temperature =
                        temperature.toDoubleOrNull() ?: 0.0,

                    humidity =
                        humidity.toIntOrNull() ?: 0,

                    pressure =
                        pressure.toDoubleOrNull() ?: 0.0,

                    windSpeed =
                        windSpeed.toDoubleOrNull() ?: 0.0,

                    condition = "Sunny",

                    notes = notes,

                    imagePath =
                        compressedResult.first,

                    originalImageSize =

                        imagePath?.let {
                            if (it.isNotBlank()) File(it).length() else 0L
                        } ?: 0L,

                    compressedImageSize =
                        compressedResult.second,

                    timestamp =
                        System.currentTimeMillis()
                )

                viewModel.saveReport(report)

                navController.navigate(Routes.REPORTS) {
                    popUpTo(Routes.WEATHER) { inclusive = false }
                }
            }
        )

    }
}