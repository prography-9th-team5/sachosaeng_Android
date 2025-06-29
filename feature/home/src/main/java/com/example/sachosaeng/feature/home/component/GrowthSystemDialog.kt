package com.example.sachosaeng.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sachosaeng.app.core.ui.R.drawable
import com.sachosaeng.app.core.ui.R.string
import com.example.sachosaeng.core.ui.component.dialog.SachosaengTwoButtonDialog
import com.sachosaeng.app.core.ui.theme.Gs_G6

@Composable
fun GrowthSystemDialog(
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit = {},
    onDismissClick: () -> Unit = {}
) {
    SachosaengTwoButtonDialog(
        modifier = modifier,
        fontSize = 14,
        rightButtonText = stringResource(id = string.tutorial_dialog_right_button_text),
        rightButtonOnClick = onStartClick,
        leftButtonText = stringResource(id = string.tutorial_dialog_left_butotn_text),
        leftButtonOnClick = onDismissClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(vertical = 36.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconWithRoundedBackground(
                    modifier = modifier.padding(end = 8.dp),
                    iconResId = drawable.ic_student_lv1,
                    contentDescription = null
                )
                Image(
                    modifier = modifier.padding(end = 8.dp),
                    painter = painterResource(id = drawable.ic_round_right_arrow),
                    contentDescription = null
                )
                IconWithRoundedBackground(
                    modifier = modifier.padding(end = 8.dp),
                    iconResId = drawable.ic_student_lv2,
                    contentDescription = null
                )
            }
            Text(
                modifier = modifier.padding(top = 14.dp),
                textAlign = TextAlign.Center,
                text = stringResource(id = string.tutorial_dialog_content_for_the_growth_system),
                fontSize = 14.sp,
                color = Gs_G6,
                fontWeight = FontWeight.W600
            )
        }
    }
}

@Composable
private fun IconWithRoundedBackground(
    modifier: Modifier = Modifier,
    iconResId: Int,
    contentDescription: String? = null
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .background(Gs_G6)
            .size(69.dp)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = contentDescription
        )
    }
}

@Preview
@Composable
fun GrowthSystemDialogPreview() {
    GrowthSystemDialog()
}