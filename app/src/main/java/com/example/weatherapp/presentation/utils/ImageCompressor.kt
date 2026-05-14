package com.example.weatherapp.presentation.utils


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream

object ImageCompressor {

    fun compressImage(
        context: Context,
        imagePath: String
    ): Pair<String, Long> {

        val file = File(imagePath)
        if (!file.exists()) {
            return Pair("", 0L)
        }

        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            ?: return Pair("", 0L)

        val compressedFile = File(
            context.cacheDir,
            "compressed_${file.name}"
        )

        try {
            val outputStream = FileOutputStream(compressedFile)
            bitmap.compress(
                Bitmap.CompressFormat.JPEG,
                50,
                outputStream
            )
            outputStream.flush()
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
            return Pair("", 0L)
        }

        return Pair(
            compressedFile.absolutePath,
            compressedFile.length()
        )
    }
}