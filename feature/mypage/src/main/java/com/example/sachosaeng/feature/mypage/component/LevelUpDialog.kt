package com.example.sachosaeng.feature.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.R.drawable.bg_level_up
import com.sachosaeng.app.core.ui.R.drawable.ic_level_up
import com.sachosaeng.app.core.ui.R.drawable.ic_star_1
import com.sachosaeng.app.core.ui.R.drawable.ic_star_2
import com.sachosaeng.app.core.ui.R.drawable.ic_star_3
import com.sachosaeng.app.core.ui.component.dialog.SachosaengOneButtonDialog
import com.sachosaeng.app.core.ui.theme.Pc_4_Yellow

@Composable
fun LevelUpDialog(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    SachosaengOneButtonDialog(
        backgroundColor = Pc_4_Yellow,
        buttonText = stringResource(R.string.confirm_label),
        buttonOnClick = onClick
    ) {
            Box(contentAlignment = Alignment.TopCenter, modifier = modifier.padding(bottom = 20.dp)) {
                Image(
                    modifier = modifier.align(Alignment.TopCenter),
                    painter = painterResource(bg_level_up),
                    contentDescription = "",
                )
                Image(
                    modifier = modifier.align(Alignment.Center),
                    painter = painterResource(ic_level_up),
                    contentDescription = "",
                )
                Image(
                    modifier = modifier.align(Alignment.TopStart).padding(top = 20.dp),
                    painter = painterResource(ic_star_1),
                    contentDescription = "",
                )
                Image(
                    modifier = modifier.align(Alignment.CenterEnd),
                    painter = painterResource(ic_star_2),
                    contentDescription = "",
                )
                Image(
                    modifier = modifier.align(Alignment.CenterStart).padding(top = 50.dp, start = 20.dp),
                    painter = painterResource(ic_star_3),
                    contentDescription = "",
                )
                Text(
                    modifier = modifier.align(Alignment.BottomCenter).padding(top = 130.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W600,
                    fontSize = 14.sp,
                    text = stringResource(R.string.mypage_download_profile_image_description)
                )
        }
    }
}

@Preview
@Composable
fun LevelUpDialogPreview() {
    LevelUpDialog(
        onClick = {},
    )
}