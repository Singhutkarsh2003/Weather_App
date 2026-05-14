package com.example.weatherapp.presentation.savedreports.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.weatherapp.domain.model.WeatherReport
import java.io.File

@Composable
fun ReportCard(

    report: WeatherReport

) {

    Card(

        modifier = Modifier
            .fillMaxWidth()

    ) {

        Column(

            modifier = Modifier
                .padding(16.dp)

        ) {

            Image(
                 painter = rememberAsyncImagePainter(
                File(report.imagePath)
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(180.dp),
                contentScale = ContentScale.Crop
            )

            Text(

                text = report.cityName,

                style =
                    MaterialTheme.typography.titleMedium
            )

            Text(
                text =
                    "Temperature: ${report.temperature} °C"
            )

            Text(
                text =
                    "Humidity: ${report.humidity}%"
            )

            Text(
                text =
                    "Pressure: ${report.pressure}"
            )

            Text(
                text =
                    "Wind Speed: ${report.windSpeed}"
            )

            Text(
                text =
                    "Condition: ${report.condition}"
            )

            Text(
                text =
                    "Notes: ${report.notes}"
            )
        }
    }
}