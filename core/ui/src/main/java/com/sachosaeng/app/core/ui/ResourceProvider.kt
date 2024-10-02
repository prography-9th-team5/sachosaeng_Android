package com.sachosaeng.app.core.ui

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
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