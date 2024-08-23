package com.example.sachosaeng.core.ui.component.topappbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sachosaeng.core.ui.R

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
fun SachosaengTopAppBarWithProfile(
    title: String,
    navigateToBackStack: () -> Unit = {},
    profileImageUrl: String? = null
) {
    SachosaengTopAppBar(
        componentRow = {
            Row(
                modifier = Modifier.fillMaxWidth(0.55f),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(0.55f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Image(
                        modifier = Modifier.clickable { navigateToBackStack() },
                        painter = painterResource(id = R.drawable.ic_go_back),
                        contentDescription = null
                    )
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                    )
                    ProfileImage(profileImageUrl)
                }
            }
        }
    )
}

@Composable
fun ProfileImage(profileImageUrl: String?) {
    AsyncImage(
        modifier = Modifier.size(40.dp),
        model = profileImageUrl ?: R.drawable.ic_default_profile,
        contentDescription = "",
    )
}