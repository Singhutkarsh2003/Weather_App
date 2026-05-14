package com.example.weatherapp.presentation.report.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import java.io.File


@Composable
fun ImagePreview(

    imagePath: String?

) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)

    ) {

        if (imagePath == null) {

            Text(
                text = "No Image Captured"
            )

        } else {

            Image(

                painter =
                    rememberAsyncImagePainter(
                        File(imagePath)
                    ),

                contentDescription = null,

                modifier = Modifier
                    .fillMaxWidth(),

                contentScale =
                    ContentScale.Crop
            )
        }
    }
}