package com.sachosaeng.app.feature.webview

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

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
        WebViewTopAppBar(
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

@Composable
internal fun WebViewTopAppBar(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
        IconButton(onClick = { onCloseClick() }) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
    }
}
