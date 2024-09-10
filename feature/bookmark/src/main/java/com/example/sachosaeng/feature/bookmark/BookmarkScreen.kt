package com.example.sachosaeng.feature.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.ui.component.topappbar.ProfileImage
import com.example.sachosaeng.core.ui.theme.Gs_White
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()
    BookmarkScreen(
        state = state.value
    )
}

@Composable
internal fun BookmarkScreen(
    state: BookmarkScreenUiState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Topbar(state.userType)
    }
}

@Composable
private fun Topbar(
    userType: UserType,
    onProfileImageClicked: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.bookmark_screen_title),
            fontSize = 26.sp,
            fontWeight = FontWeight.W700
        )
        ProfileImage(userType, onClick = {
            onProfileImageClicked()
        })
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    BookmarkScreen(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gs_White),
        state = BookmarkScreenUiState(
            userType = UserType.NEW_EMPLOYEE,
        )
    )
}