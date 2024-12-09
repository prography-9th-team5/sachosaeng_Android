package com.sachosaeng.app.feature.webview

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.sachosaeng.core.ui.component.topappbar.SachosaengTopAppBarWithCloseButton

@Composable
fun WebViewScreen(
    url: String,
    onCloseClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        SachosaengTopAppBarWithCloseButton(
            onCloseClick = onCloseClick
        )
        WebViewContent(url = url)
    }
}


@Composable
internal fun WebViewContent(url: String) {
    val context = LocalContext.current
    val webView = remember { WebView(context) }

    AndroidView(factory = {
        webView.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.run {
                javaScriptEnabled = true
                domStorageEnabled = true
            }
            webViewClient = WebViewClient() // Add this line
        }
    }, update = {
        it.loadUrl(url)
    })
}
