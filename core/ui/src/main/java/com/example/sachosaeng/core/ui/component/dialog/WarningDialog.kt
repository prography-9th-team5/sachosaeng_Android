package com.example.sachosaeng.core.ui.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.component.dialog.SachosaengOneButtonDialog
import com.sachosaeng.app.core.ui.theme.Gs_Black

@Composable
fun WarningDialog(
    confirmLabel: String,
    onConfirm: () -> Unit,
    errorMessage: String,
    modifier: Modifier = Modifier,
) {
    SachosaengOneButtonDialog(
        modifier = modifier,
        buttonText = confirmLabel,
        buttonOnClick = { onConfirm() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(vertical = 36.dp)
                .fillMaxWidth(0.8f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_warning),
                contentDescription = null
            )
            Text(
                modifier = modifier.padding(top = 12.dp),
                textAlign = TextAlign.Center,
                text = errorMessage,
                color = Gs_Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
        }
    }
}