package com.example.sachosaeng.core.ui.extension

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

suspend fun Activity.captureComposableAsBitmap(
    widthDp: Dp,
    heightDp: Dp,
    content: @Composable () -> Unit
): Bitmap = withContext(Dispatchers.Main) {
    val density = resources.displayMetrics.density
    val widthPx = (widthDp.value * density).toInt()
    val heightPx = (heightDp.value * density).toInt()

    val composeView = ComposeView(this@captureComposableAsBitmap)

    val container = FrameLayout(this@captureComposableAsBitmap).apply {
        alpha = 0f
        visibility = View.VISIBLE
        addView(composeView, ViewGroup.LayoutParams(widthPx, heightPx))
    }

    this@captureComposableAsBitmap.addContentView(
        container,
        ViewGroup.LayoutParams(widthPx, heightPx)
    )

    suspendCancellableCoroutine<Unit> { cont ->
        if (composeView.isAttachedToWindow) {
            cont.resume(Unit)
        } else {
            composeView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
                override fun onViewAttachedToWindow(v: View) {
                    composeView.removeOnAttachStateChangeListener(this)
                    cont.resume(Unit)
                }

                override fun onViewDetachedFromWindow(v: View) {}
            })
        }
    }

    composeView.setContent {
        MaterialTheme {
            Box(modifier = Modifier.size(widthDp, heightDp)) {
                content()
            }
        }
    }

    composeView.measure(
        View.MeasureSpec.makeMeasureSpec(widthPx, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(heightPx, View.MeasureSpec.EXACTLY)
    )
    composeView.layout(0, 0, widthPx, heightPx)

    val bitmap = Bitmap.createBitmap(widthPx, heightPx, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    composeView.draw(canvas)

    (container.parent as? ViewGroup)?.removeView(container)

    bitmap
}