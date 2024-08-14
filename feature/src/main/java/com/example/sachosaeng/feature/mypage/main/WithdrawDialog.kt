package com.example.sachosaeng.feature.mypage.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.sachosaeng.core.ui.component.dialog.SachosaengTwoButtonDialog
import com.example.sachosaeng.core.ui.theme.SachosaengTheme
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.feature.R.drawable

@Composable
fun WithdrawDialog(
    modifier: Modifier = Modifier,
    onWithdraw: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    SachosaengTwoButtonDialog(
        modifier = modifier,
        leftButtonText = stringResource(id = R.string.confirm_label),
        leftButtonOnClick = { onWithdraw() },
        rightButtonText = stringResource(id = R.string.cancel_label),
        rightButtonOnClick = { onCancel() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(vertical = 36.dp)
                .fillMaxWidth(0.8f)
        ) {
            Image(
                painter = painterResource(id = drawable.ic_warning),
                contentDescription = null
            )
            Text(
                modifier = modifier.padding(top = 12.dp),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.withdraw_dialog_content),
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
        }
    }
}

@Composable
@Preview
fun WithdrawDialogPreview() {
    SachosaengTheme {
        WithdrawDialog()
    }
}