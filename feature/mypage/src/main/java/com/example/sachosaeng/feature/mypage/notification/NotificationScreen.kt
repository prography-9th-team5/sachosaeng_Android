package com.example.sachosaeng.feature.mypage.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.ui.HtmlAndroidView
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.noRippleClickable
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.ui.theme.Gs_G6
import com.sachosaeng.app.core.ui.theme.Gs_White
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
    viewModel: NotificationViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    Column(
        modifier = Modifier
            .background(Gs_G2)
            .padding(20.dp)
    ) {

        LazyColumn(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Gs_White)
                .weight(1f)
                .padding(20.dp),
        ) {
            item {
                Box(
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = state.title,
                        color = Gs_G6,
                        fontWeight = FontWeight.W700,
                        modifier = modifier
                            .align(Alignment.Center)
                            .padding(bottom = 45.dp)
                    )
                    Image(
                        painterResource(R.drawable.ic_close), contentDescription = "",
                        modifier = modifier
                            .noRippleClickable { onClose() }
                            .align(Alignment.TopEnd),
                    )
                }
            }
            item {
                HtmlAndroidView(content = state.contents)
            }
        }
    }
}

@Composable
@Preview
fun NotificationScreenPreview() {
    NotificationScreen(
        onClose = {}
    )
}
