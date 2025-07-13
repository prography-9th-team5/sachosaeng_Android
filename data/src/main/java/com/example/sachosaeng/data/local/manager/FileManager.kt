package com.example.sachosaeng.data.local.manager

import android.graphics.Bitmap

interface FileManager {
    suspend fun downloadImage(bitmap: Bitmap, fileName: String): Result<Unit>
}