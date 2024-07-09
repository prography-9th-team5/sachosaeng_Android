package com.example.sachosaeng.feature.vote

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.domain.model.Vote
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.component.topappbar.SachosaengTopAppBar
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.feature.R.string
import com.example.sachosaeng.core.ui.R.drawable

@Composable
fun VoteScreen(
    navigateToBackStack: () -> Unit,
    viewModel: VoteDetailViewModel = hiltViewModel()
) {
    //todo: viewModel.container.stateFlow.collectAsState() -> viewModel.collectAsState()
    val state = viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getVoteContent()
    }
    VoteScreen(
        vote = state.value,
        navigateToBackStack = navigateToBackStack,
        onBookmarkVote = { viewModel.bookmarkVote() },
        onVoteComplete = { selectedOption -> viewModel.vote(selectedOption) }
    )
}

@Composable
internal fun VoteScreen(
    vote: Vote,
    onBookmarkVote: () -> Unit = { },
    onVoteComplete: (String) -> Unit = { },
    navigateToBackStack: () -> Unit,
) {
    var selectedOption by remember { mutableStateOf(vote.selectedOption) }

    Column(
        modifier = Modifier
            .background(Gs_G2)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        SachosaengTopAppBar(
            componentRow = {
                Row(
                    modifier = Modifier.fillMaxWidth(0.55f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Image(
                        modifier = Modifier.clickable { navigateToBackStack() },
                        painter = painterResource(id = drawable.ic_go_back),
                        contentDescription = null
                    )
                    Text(
                        text = vote.category.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700,
                    )
                }
            }, profileImageUrl = null
        )
        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            VoteDetailCard(
                isBookmarked = vote.isBookmarked,
                onBookmarkButtonClicked = { onBookmarkVote() },
                selectedOption = selectedOption,
                onSelectedOption = { selectedOption = it },
                vote = vote
            )
            SachoSaengButton(
                enabled = selectedOption.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = string.confirm_label),
                onClick = { onVoteComplete(selectedOption) }
            )
        }
    }
}

@Preview
@Composable
fun VoteScreenPreview() {
    VoteScreen(vote = Vote(
        title = "친한 사수분 결혼식 축의금 얼마가 좋을까요?",
        count = 1000,
        option = listOf("옵션1", "옵션2", "옵션3"),
        category = com.example.sachosaeng.core.domain.model.Category(
            name = "카테고리",
            imageUrl = ""
        )
    ), navigateToBackStack = {})
}