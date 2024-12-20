package com.sachosaeng.app.core.ui.component.topappbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sachosaeng.app.core.ui.UserType
import com.sachosaeng.app.core.ui.noRippleClickable

@Composable
fun SachosaengTopAppBar(
    modifier: Modifier = Modifier,
    componentRow: @Composable () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        componentRow()
    }
}
@Composable
fun TopBarWithProfileImage(
    modifier: Modifier = Modifier,
    topBarContent: @Composable () -> Unit,
    userType: UserType,
    onProfileImageClicked: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
    ) {
        topBarContent()
        ProfileImage(userType, onClick = {
            onProfileImageClicked()
        })
    }
}

@Composable
fun ProfileImage(userType: UserType, onClick: () -> Unit = {}) {
    Image(
        modifier = Modifier
            .size(40.dp)
            .noRippleClickable { onClick() }
            .clip(CircleShape),
        painter = painterResource(id = userType.userTypeImageRes),
        contentDescription = "",
    )
}