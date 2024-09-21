package com.example.sachosaeng.core.ui.component.topappbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.noRippleClickable

@Composable
fun SachosaengDetailTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    fontSize: Int = 18,
    fontWeight: FontWeight = FontWeight.W700,
    navigateToBackStack: (() -> Unit)? = null,
    tailingComponent: @Composable (() -> Unit)? = null
) {
    SachosaengTopAppBar(
        modifier = modifier,
        componentRow = {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                navigateToBackStack?.let {
                    Image(
                        modifier = modifier.noRippleClickable { navigateToBackStack() },
                        painter = painterResource(id = R.drawable.ic_go_back),
                        contentDescription = null
                    )
                }
                Text(
                    modifier = modifier.weight(1f),
                    text = title,
                    fontSize = fontSize.sp,
                    fontWeight = fontWeight,
                    textAlign = TextAlign.Center
                )
                tailingComponent?.invoke()
            }
        }
    )
}