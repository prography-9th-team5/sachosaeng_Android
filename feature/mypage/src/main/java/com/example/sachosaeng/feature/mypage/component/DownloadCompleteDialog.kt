package com.example.sachosaeng.feature.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import com.sachosaeng.app.core.ui.component.dialog.SachosaengOneButtonDialog
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.R.drawable.ic_circle_download

@Composable
fun DownloadCompleteDialog(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    SachosaengOneButtonDialog(
        buttonText = stringResource(R.string.confirm_label),
        buttonOnClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(ic_circle_download),
                contentDescription = "",
                modifier = modifier.padding(top = 43.dp)
            )
            Text(
                modifier = modifier.padding(top = 9.dp, bottom = 43.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W600,
                fontSize = 14.sp,
                text = stringResource(R.string.mypage_download_profile_image_description)
            )
        }
    }
}