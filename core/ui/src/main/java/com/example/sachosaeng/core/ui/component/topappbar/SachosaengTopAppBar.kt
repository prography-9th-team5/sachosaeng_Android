package com.example.sachosaeng.core.ui.component.topappbar

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.UserType

@Composable
fun SachosaengTopAppBar(
    componentRow: @Composable () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
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
            .clickable { onClick() }
            .clip(CircleShape),
        painter = painterResource(id = userType.userTypeImageRes),
        contentDescription = "",
    )
}