package com.example.sachosaeng.feature.webview

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

const val ROUTE_WEBVIEW = "webview"
const val ARG_URL = "url"

fun NavController.navigateToWebView(url: String) {
    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
    navigate("${com.example.sachosaeng.feature.webview.ROUTE_WEBVIEW}?${com.example.sachosaeng.feature.webview.ARG_URL}=$encodedUrl")
}

fun NavGraphBuilder.addWebViewScreen(
    navController: NavController
) {
    composable(
        route = "${com.example.sachosaeng.feature.webview.ROUTE_WEBVIEW}?${com.example.sachosaeng.feature.webview.ARG_URL}={${com.example.sachosaeng.feature.webview.ARG_URL}}"
    ) { backStackEntry ->
        val url = backStackEntry.arguments?.getString("${com.example.sachosaeng.feature.webview.ARG_URL}")?.let {
            URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
        } ?: ""
        WebViewScreen(
            url = url,
            onCloseClick = { navController.popBackStack() },
        )
    }
}