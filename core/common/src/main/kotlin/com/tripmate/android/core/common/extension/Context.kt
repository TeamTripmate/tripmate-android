package com.tripmate.android.core.common.extension

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Environment
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream

fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION,
    ) == PackageManager.PERMISSION_GRANTED
}

fun Context.externalShareForBitmap(bitmap: Bitmap) {
    try {
        val imagePath = File(getExternalFilesDir(null)?.absolutePath + "/ShareImgFolder")
        imagePath.mkdirs()

        val shareIntent = Intent().apply {
            val file = saveImageIntoFileFromUri(bitmap, "img_${System.currentTimeMillis()}.jpg", getExternalFilePath())
            val photoUri = FileProvider.getUriForFile(applicationContext, applicationContext.packageName + ".provider", file)

            action = Intent.ACTION_SEND
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, photoUri)
        }

        startActivity(Intent.createChooser(shareIntent, ""))
    } catch (e: Exception) {
        Timber.e("[externalShareFoBitmap] message: ${e.message}")
    }
}

private fun getExternalFilePath(): String {
    return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString().plus(File.separator)
}

private fun saveImageIntoFileFromUri(bitmap: Bitmap, fileName: String, path: String): File {
    val file = File(path, fileName)
    try {
        val fileOutputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
        fileOutputStream.flush()
        fileOutputStream.close()
    } catch (e: Exception) {
        Timber.e("[saveImageIntoFileFromUri] message: ${e.message}")
    }

    return file
}
