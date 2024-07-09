package com.example.sachosaeng.core.util.extension

object StringExtension {
    fun String.toColorResource(): Int {
        return this.toLong(16).toInt()
    }
}