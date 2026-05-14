package com.example.weatherapp.presentation.camera

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.weatherapp.presentation.utils.ImageStorage
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale


@SuppressLint("UnsafeOptInUsageError")
@Composable
fun CameraScreen() {

    val context = LocalContext.current

    val previewView = remember {
        PreviewView(context)
    }

    val imageCapture = remember {
        ImageCapture.Builder().build()
    }

    LaunchedEffect(Unit) {

        startCamera(

            context = context,

            previewView = previewView,

            imageCapture = imageCapture
        )
    }

    Column(

        modifier = Modifier
            .fillMaxSize(),

        verticalArrangement =
            Arrangement.spacedBy(16.dp)

    ) {

        CameraPreview(

            previewView = previewView,

            modifier = Modifier
                .fillMaxWidth()
                .height(550.dp)
        )

        Button(

            onClick = {

                capturePhoto(

                    context = context,

                    imageCapture = imageCapture
                )
            },

            modifier = Modifier
                .fillMaxWidth()

        ) {

            Text(
                text = "Capture Photo"
            )
        }
    }
}

private fun startCamera(

    context: Context,

    previewView: PreviewView,

    imageCapture: ImageCapture

) {

    val cameraProviderFuture =
        ProcessCameraProvider
            .getInstance(context)

    cameraProviderFuture.addListener({

        val cameraProvider =
            cameraProviderFuture.get()

        val preview =
            Preview.Builder()
                .build()

        preview.surfaceProvider =
            previewView.surfaceProvider

        val cameraSelector =
            CameraSelector.DEFAULT_BACK_CAMERA

        try {

            cameraProvider.unbindAll()

            cameraProvider.bindToLifecycle(

                context as androidx.lifecycle.LifecycleOwner,

                cameraSelector,

                preview,

                imageCapture
            )

        } catch (e: Exception) {

            Log.e(
                "CameraX",
                "Binding failed",
                e
            )
        }

    }, ContextCompat.getMainExecutor(context))
}

private fun capturePhoto(

    context: Context,

    imageCapture: ImageCapture

) {

    val photoFile = File(

        context.cacheDir,

        SimpleDateFormat(

            "yyyyMMdd_HHmmss",

            Locale.US

        ).format(System.currentTimeMillis()) + ".jpg"
    )

    val outputOptions =
        ImageCapture.OutputFileOptions
            .Builder(photoFile)
            .build()

    imageCapture.takePicture(

        outputOptions,

        ContextCompat.getMainExecutor(context),

        object :
            ImageCapture.OnImageSavedCallback {

            override fun onImageSaved(

                outputFileResults:
                ImageCapture.OutputFileResults

            ) {

                ImageStorage.latestCapturedImage =
                    photoFile.absolutePath

                Log.d(

                    "CameraX",

                    "Photo Saved: ${photoFile.absolutePath}"
                )
            }

            override fun onError(

                exception:
                androidx.camera.core.ImageCaptureException

            ) {

                Log.e(

                    "CameraX",

                    "Capture failed",

                    exception
                )
            }
        }
    )
}