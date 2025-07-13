package com.example.sachosaeng.core.ui

import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.sachosaeng.app.core.ui.theme.Gs_G6

@Composable
fun HtmlAndroidView(
    content: String,
) {
    val htmlWithBreaks = content.replace("\n", "<br>")
    AndroidView(
        factory = { context ->
            TextView(context).apply {
                text = HtmlCompat.fromHtml(
                    htmlWithBreaks,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
                textSize = 16f
                setTextColor(Gs_G6.toArgb())
            }
        },
        update = { textView ->
            textView.text = HtmlCompat.fromHtml(htmlWithBreaks, HtmlCompat.FROM_HTML_MODE_LEGACY)
        },
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
    )
}