package com.example.sachosaeng.feature.home.component

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.R.drawable
import com.example.sachosaeng.core.ui.R.string
import com.example.sachosaeng.core.ui.component.dialog.SachosaengOneButtonDialog
import com.example.sachosaeng.core.ui.theme.Gs_G6


@Composable
fun TodaysVoteDialog(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    SachosaengOneButtonDialog(
        modifier = modifier,
        buttonText = stringResource(id = string.vote_label),
        buttonOnClick = onClick,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(vertical = 36.dp)
        ) {
            Image(
                painter = painterResource(id = drawable.image_todays_vote_dialog),
                contentDescription = null
            )
            Text(
                modifier = modifier.padding(top = 14.dp),
                textAlign = TextAlign.Center,
                text = stringResource(id = string.todays_vote_dialog_description),
                fontSize = 16.sp,
                color = Gs_G6,
                fontWeight = FontWeight.W600
            )
        }
    }
}

@Preview
@Composable
fun TodaysVoteDialogPreview() {
    TodaysVoteDialog()
}