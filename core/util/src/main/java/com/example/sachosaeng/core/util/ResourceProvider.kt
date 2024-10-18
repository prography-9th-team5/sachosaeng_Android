package com.example.sachosaeng.core.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProvider @Inject constructor(
    @ApplicationContext val context: Context
) {
    fun getString(id: Int): String {
        return context.resources.getString(id)
    }
    fun getColor(id: Int): Int {
        return context.resources.getColor(id, null)
    }
}