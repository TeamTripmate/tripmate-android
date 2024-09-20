package com.tripmate.android.core.common.utils

import android.graphics.Bitmap
import android.graphics.LinearGradient
import android.graphics.Picture
import android.graphics.Shader
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import timber.log.Timber

@Suppress("TooGenericExceptionCaught")
fun createBitmapFromPicture(
    picture: Picture,
    colors: List<Color>,
    stops: List<Float>,
    height: Float,
): Bitmap? {
    try {
        val bitmap = Bitmap.createBitmap(
            picture.width,
            picture.height,
            Bitmap.Config.ARGB_8888,
        )
        val canvas = android.graphics.Canvas(bitmap)

        // Create Android Shader
        val shader = LinearGradient(
            0f,
            0f,
            0f,
            height,
            colors.map { it.toArgb() }.toIntArray(),
            stops.toFloatArray(),
            Shader.TileMode.CLAMP,
        )

        // Draw gradient
        val paint = android.graphics.Paint()
        paint.shader = shader
        canvas.drawRect(0f, 0f, picture.width.toFloat(), picture.height.toFloat(), paint)

        // Draw picture content
        canvas.drawPicture(picture)

        return bitmap
    } catch (e: Exception) {
        Timber.e("[createBitmapFromPicture] ${e.message}")
        return null
    }
}
