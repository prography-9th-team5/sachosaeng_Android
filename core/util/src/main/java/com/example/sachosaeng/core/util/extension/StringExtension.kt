package com.example.sachosaeng.core.util.extension

import com.example.sachosaeng.core.util.constant.ColorConstant.GS_BLACK_CODE
import android.graphics.Color as AndroidColor

object StringExtension {
    fun String.toColorResource(): Int {
        return AndroidColor.parseColor(if(this.startsWith("#")) this else GS_BLACK_CODE)
    }
}