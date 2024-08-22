package com.example.sachosaeng.core.ui.component.topappbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.R

@Composable
fun SachosaengDetailTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    fontSize : Int = 18,
    fontWeight: FontWeight = FontWeight.W700,
    navigateToBackStack: () -> Unit = {}
) {
    SachosaengTopAppBar(
        componentRow = {
            Row(
                modifier = modifier
            ) {
                Image(
                    modifier = modifier.clickable { navigateToBackStack() },
                    painter = painterResource(id = R.drawable.ic_go_back),
                    contentDescription = null
                )
                Text(
                    modifier = modifier.weight(1f),
                    text = title,
                    fontSize = fontSize.sp,
                    fontWeight = fontWeight,
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}