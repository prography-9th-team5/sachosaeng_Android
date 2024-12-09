package com.example.sachosaeng.feature.vote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.ui.component.CategoryList
import com.example.sachosaeng.core.ui.component.topappbar.SachosaengTopAppBarWithCloseButton
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.ui.theme.Gs_G5
import com.sachosaeng.app.core.ui.theme.Gs_G6
import com.sachosaeng.app.core.ui.theme.Gs_White
import com.sachosaeng.app.feature.vote.component.OptionRow

@Composable
fun VotePreviewScreen(
    navigateToBackStack: () -> Unit,
    viewModel: VotePreviewViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()
    VotePreviewScreen(
        state = state,
        onCloseClick = navigateToBackStack
    )
}

@Composable
internal fun VotePreviewScreen(
    state: VotePreviewUiState,
    onCloseClick: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .background(Gs_G2)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        item {
            VotePreviewCard(state = state, onCloseClick = onCloseClick)
        }
    }
}

@Composable
private fun VotePreviewCard(
    state: VotePreviewUiState,
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Gs_White)
    ) {
        SachosaengTopAppBarWithCloseButton(
            onCloseClick = onCloseClick,
            title = stringResource(id = R.string.vote_preview)
        )
        Column(
            modifier = modifier
                .padding(20.dp)
        )  {
            Text(
                text = stringResource(id = R.string.add_vote_toast_message),
                color = Gs_G6,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
            )
            VoteDescription(
                voteDescription = state.title
            )
            CanMultipleChoiceText(
                canMultipleChoice = state.canMultipleChoice
            )
            Column(
                modifier = Modifier.padding(vertical = 28.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                state.options.forEach { option ->
                    OptionRow(
                        isVoted = false,
                        isSeleceted = false,
                        text = option
                    )
                }
            }
            Text(
                modifier = Modifier.padding(bottom = 14.dp),
                text = stringResource(id = R.string.category),
                color = Gs_Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.W700,
            )
            CategoryList(
                categories = listOf(state.category),
                selectedCategory = listOf(state.category)
            )
        }
    }
}

@Composable
private fun VoteDescription(
    modifier: Modifier = Modifier,
    voteDescription: String
) {
    Text(
        modifier = modifier.padding(top = 28.dp, bottom = 16.dp),
        text = voteDescription,
        color = Gs_Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.W700,
    )
}

@Composable
private fun CanMultipleChoiceText(
    canMultipleChoice: Boolean
) {
    val stringRes =
        stringResource(id = if (canMultipleChoice) R.string.add_vote_multiple_choice_allowed else R.string.add_vote_multiple_choice_is_not_allowed)
    Text(
        text = "($stringRes)",
        color = Gs_G5,
        fontSize = 14.sp,
        fontWeight = FontWeight.W500
    )
}

@Composable
@Preview
fun VotePreviewScreenPreview() {
    VotePreviewScreen(
        state = VotePreviewUiState(
            title = "Title",
            options = listOf("Option1", "Option2", "Option2", "Option2"),
            category = Category(1, "Category"),
            description = "Dedsdlsjdsadjadasjdisadjasdasdscription"
        ),
    )
}