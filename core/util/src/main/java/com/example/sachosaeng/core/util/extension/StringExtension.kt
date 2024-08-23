package com.example.sachosaeng.core.util.extension

import android.graphics.Color as AndroidColor

object StringExtension {
    fun String.toColorResource(): Int {
        return AndroidColor.parseColor(this)
    }
}