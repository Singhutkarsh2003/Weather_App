package com.example.weatherapp.presentation.camera

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import java.io.File


class CameraCapture {

    fun takePhoto(

        context: Context,

        imageCapture: ImageCapture,

        outputFile: File,

        onPhotoCaptured: (File) -> Unit,

        onError: (Exception) -> Unit

    ) {

        val outputOptions =
            ImageCapture.OutputFileOptions
                .Builder(outputFile)
                .build()

        imageCapture.takePicture(

            outputOptions,

            ContextCompat.getMainExecutor(
                context
            ),

            object :
                ImageCapture.OnImageSavedCallback {

                override fun onImageSaved(

                    outputFileResults:
                    ImageCapture.OutputFileResults

                ) {

                    onPhotoCaptured(outputFile)
                }

                override fun onError(

                    exception:
                    ImageCaptureException

                ) {

                    onError(exception)
                }
            }
        )
    }
}