package com.example.sachosaeng.feature.addvote

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.feature.addvote.component.CategoryList
import com.example.sachosaeng.feature.addvote.component.DefaultSmallTextField
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.component.button.SachoSaengButton
import com.sachosaeng.app.core.ui.component.topappbar.SachosaengDetailTopAppBar
import com.sachosaeng.app.core.ui.noRippleClickable
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.ui.theme.Gs_G4
import com.sachosaeng.app.core.ui.theme.Gs_G6
import com.sachosaeng.app.core.ui.theme.Gs_White
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun AddVoteScreen(
    showSnackBar: (String) -> Unit,
    navigateToBackStack: () -> Unit,
    navigateToSuggestedVoteHistory: () -> Unit,
    viewModel: AddVoteViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()
    viewModel.collectSideEffect {
        when (it) {
            is AddVoteSideEffect.NavigateToSuggestedVoteHistory -> navigateToSuggestedVoteHistory()
            is AddVoteSideEffect.ShowSnackBar -> showSnackBar(it.message)
        }
    }

    AddVoteScreen(
        state = state,
        onTitleChange = viewModel::onTitleChange,
        onOptionChanged = viewModel::onOptionSelected,
        onCategorySelected = viewModel::onCategorySelected,
        navigateToBackStack = navigateToBackStack,
        onChangeMultipleCheck = viewModel::onChangeMultipleCheck,
        onAddVoteButtonClicked = viewModel::onAddVoteButtonClicked,
    )
}

@Composable
internal fun AddVoteScreen(
    state: AddVoteUiState,
    onTitleChange: (String) -> Unit = {},
    onOptionChanged: (String, Int) -> Unit = { _, _ -> },
    onChangeMultipleCheck: () -> Unit = {},
    onCategorySelected: (Category) -> Unit = {},
    onAddVoteButtonClicked: () -> Unit = {},
    navigateToBackStack: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .background(Gs_G2)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        item {
            SachosaengDetailTopAppBar(
                navigateToBackStack = navigateToBackStack,
                title = stringResource(id = R.string.add_vote_title),
                fontWeight = FontWeight.W700,
                fontSize = 18
            )
        }
        item {
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = stringResource(id = R.string.add_vote_description),
                color = Gs_G6,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
            )
        }
        item {
            Title(resId = R.string.vote_title)
            DefaultTextField(value = state.title, onValueChange = onTitleChange)
        }
        item {
            AddOptionTitleRow(
                canMultipleCheck = state.canMultipleCheck,
                onChangeMultipleCheck = onChangeMultipleCheck
            )
        }
        items(4) {
            DefaultSmallTextField(
                value = state.options[it],
                onValueChange = { value -> onOptionChanged(value, it) },
                placeholder = stringResource(id = R.string.vote_option),
            )
        }
        item {
            Title(
                resId = R.string.vote_category
            )
            CategoryList(
                selectedCategory = listOf(state.selectedCategory),
                categories = state.category,
                onCategorySelected = onCategorySelected
            )
            Spacer(modifier = Modifier.padding(top = 40.dp))
        }
        item {
            AddVoteButton(
                enabled = state.isAddButtonEnabled,
                onClickButton = onAddVoteButtonClicked
            )
        }
    }
}

@Composable
private fun Title(
    modifier: Modifier = Modifier,
    resId: Int
) {
    Text(
        modifier = modifier.padding(top = 36.dp, bottom = 14.dp),
        text = stringResource(id = resId),
        color = Gs_Black,
        fontSize = 15.sp,
        fontWeight = FontWeight.W700,
    )
}

@Composable
private fun DefaultTextField(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = Gs_G4,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = Gs_White,
            unfocusedContainerColor = Gs_White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Gs_Black
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 62.dp)
    )
}

@Composable
private fun AddOptionTitleRow(
    canMultipleCheck: Boolean,
    onChangeMultipleCheck: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Title(
            resId = R.string.vote_option
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .noRippleClickable {
                    onChangeMultipleCheck()
                }
                .padding(top = 36.dp, bottom = 14.dp),
            painter = painterResource(id = if (canMultipleCheck) R.drawable.ic_checked_circle else R.drawable.ic_unchecked_circle),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.add_vote_multiple_choice_allowed),
            color = Gs_Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(start = 6.dp, top = 36.dp, bottom = 14.dp)
        )
    }
}

@Composable
private fun AddVoteButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClickButton: () -> Unit
) {
    SachoSaengButton(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        text = stringResource(id = R.string.complete_label),
        enabled = enabled,
        onClick = onClickButton
    )
}

@Composable
@Preview
fun AddVoteScreenPreview() {
    AddVoteScreen(
        state = AddVoteUiState(
            title = "Title",
            options = listOf("Option1", "Option2", "Option2", "Option2"),
            category = listOf(Category(1, "Category")),
        ),
        onTitleChange = {},
        onCategorySelected = {},
        navigateToBackStack = {}
    )
}