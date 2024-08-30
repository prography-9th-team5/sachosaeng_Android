package com.example.sachosaeng.feature.signup.signupcomplete

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.core.ui.R.string
import com.example.sachosaeng.core.ui.UserType
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SignUpCompleteScreen(
    navigateToMain: () -> Unit,
    viewModel: SignUpCompleteViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    when (state.isShow) {
        true -> SignUpCompleteScreen(state = state)
        false -> navigateToMain()
    }
}

@Composable
internal fun SignUpCompleteScreen(
    modifier: Modifier = Modifier,
    state: SignUpCompleteUiState,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Gs_White),
    ) {
        val (description, completeMessage, image) = createRefs()
        Text(
            modifier = modifier.constrainAs(description) {
                top.linkTo(parent.top)
                bottom.linkTo(image.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = stringResource(id = string.welcome_text, state.userEmail),
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            fontWeight = FontWeight.W700
        )
        Text(
            modifier = modifier
                .padding(top = 14.dp, bottom = 45.dp)
                .constrainAs(
                    completeMessage
                ) {
                    top.linkTo(description.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            text = stringResource(id = string.signup_complete_text, state.userEmail),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
        Image(
            modifier = modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            painter = painterResource(id = state.userType.userTypeImageRes),
            contentDescription = null
        )
    }
}

@Composable
@Preview
fun SignUpCompleteScreenPreview() {
    SignUpCompleteScreen(
        state = SignUpCompleteUiState(
            userEmail = "SachoSaeng"
        )
    )
}