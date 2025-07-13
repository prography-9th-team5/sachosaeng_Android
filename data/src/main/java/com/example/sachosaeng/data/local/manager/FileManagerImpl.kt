package com.example.sachosaeng.data.local.manager

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.io.OutputStream
import javax.inject.Inject

class FileManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : FileManager {
    companion object {
        const val PNG_TYPE = "image/png"
        const val FOLDER_PATH = "Pictures/SachoSaeng"
    }
    override suspend fun downloadImage(bitmap: Bitmap, fileName: String): Result<Unit> {
        return runCatching {
            val filename = "$fileName.png"
            val fos: OutputStream

            println("11111111$bitmap")
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, filename)
                put(MediaStore.Images.Media.MIME_TYPE, PNG_TYPE)
                put(MediaStore.Images.Media.RELATIVE_PATH, FOLDER_PATH)
            }

            val uri = context.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues
            ) ?: throw IOException("Failed to create new MediaStore record")

            fos = context.contentResolver.openOutputStream(uri)
                ?: throw IOException("Failed to get output stream")

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.flush()
            fos.close()
        }
    }
}