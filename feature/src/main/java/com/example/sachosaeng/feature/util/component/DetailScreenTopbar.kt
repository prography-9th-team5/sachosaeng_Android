package com.example.sachosaeng.feature.util.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.theme.Gs_Black

@Composable
fun DetailScreenTopbar(pageLabel: String, navigateToBackStack: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 26.dp)
    ) {
        Image(
            modifier = Modifier
                .clickable { navigateToBackStack() },
            painter = painterResource(id = R.drawable.ic_go_back),
            contentDescription = null
        )
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            text = pageLabel,
            fontSize = 18.sp,
            color = Gs_Black,
            fontWeight = FontWeight.W700,
        )
    }
}