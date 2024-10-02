package com.sachosaeng.app.core.util.extension

import com.sachosaeng.app.core.util.constant.ColorConstant.GS_BLACK_CODE
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import android.graphics.Color as AndroidColor

object StringExtension {
    fun String.toColorResource(): Int {
        return AndroidColor.parseColor(if(this.startsWith("#")) this else GS_BLACK_CODE)
    }
    fun String.urlEncode(): String {
        return URLEncoder.encode(this, StandardCharsets.UTF_8.toString()).replace("+", "%20")
    }
}