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
    navigate("$ROUTE_WEBVIEW?$ARG_URL=$encodedUrl")
}

fun NavGraphBuilder.addWebViewScreen(
    navController: NavController
) {
    composable(
        route = "${ROUTE_WEBVIEW}?$ARG_URL={$ARG_URL}"
    ) { backStackEntry ->
        val url = backStackEntry.arguments?.getString("$ARG_URL")?.let {
            URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
        } ?: ""
        WebViewScreen(
            url = url,
            onCloseClick = { navController.popBackStack() },
        )
    }
}