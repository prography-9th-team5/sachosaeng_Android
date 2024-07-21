package com.example.sachosaeng.feature.signup.signupcomplete

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import com.example.sachosaeng.feature.R
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
            text = stringResource(id = R.string.welcome_text, state.userName),
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
            text = stringResource(id = R.string.signup_complete_text, state.userName),
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
            painter = painterResource(id = R.drawable.ic_signup_complete_newcomer),
            contentDescription = null
        )
    }
}

@Composable
@Preview
fun SignUpCompleteScreenPreview() {
    SignUpCompleteScreen(
        state = SignUpCompleteUiState(
            userName = "SachoSaeng"
        )
    )
}