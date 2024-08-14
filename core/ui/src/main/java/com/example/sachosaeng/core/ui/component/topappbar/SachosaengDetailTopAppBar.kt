package com.example.sachosaeng.core.ui.component.topappbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.R

@Composable
fun SachosaengDetailTopAppBar(
    title: String,
    navigateToBackStack: () -> Unit = {}
) {
    SachosaengTopAppBar(
        componentRow = {
            Row(
                modifier = Modifier.fillMaxWidth(0.55f),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Image(
                    modifier = Modifier.clickable { navigateToBackStack() },
                    painter = painterResource(id = R.drawable.ic_go_back),
                    contentDescription = null
                )
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                )
            }
        }, profileImageUrl = null
    )
}