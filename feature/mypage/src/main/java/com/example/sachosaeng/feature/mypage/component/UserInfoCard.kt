package com.sachosaeng.app.feature.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sachosaeng.app.core.ui.UserType
import com.sachosaeng.app.core.ui.noRippleClickable
import com.sachosaeng.app.core.ui.theme.Gs_G6
import com.sachosaeng.app.core.ui.theme.Gs_White
import com.sachosaeng.app.feature.mypage.R

@Composable
fun UserInfoCard(userName: String, userType: UserType, userInfoModifyButtonClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Gs_G6, shape = RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.Bottom
        ) {
            Image(
                modifier = Modifier
                    .width(width = 52.dp),
                contentDescription = "",
                painter = painterResource(id = userType.userTypeIconImageRes),
            )
            Column(
                modifier = Modifier.padding(top = 23.dp, start = 20.dp, bottom = 23.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = userType.userTypeLabelRes),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500,
                    color = Gs_White
                )
                Text(
                    text = userName,
                    color = Gs_White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700
                )
            }
        }
        Image(
            modifier = Modifier
                .noRippleClickable { userInfoModifyButtonClick() }
                .align(Alignment.TopEnd)
                .padding(top = 12.dp, end = 12.dp),
            painter = painterResource(id = R.drawable.ic_modify),
            contentDescription = null
        )
    }
}