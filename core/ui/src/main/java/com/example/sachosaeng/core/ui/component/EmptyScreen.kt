package com.sachosaeng.app.feature.bookmark.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sachosaeng.app.core.ui.IntConstant.BOTTOM_BAR_HEIGHT
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.theme.Gs_G6

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    emptyLabel: String = ""
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = BOTTOM_BAR_HEIGHT.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_bookmark_empty),
            contentDescription = null
        )
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp),
            text = emptyLabel,
            color = Gs_G6,
            fontWeight = FontWeight.W600,
            fontSize = 14.sp
        )
    }
}