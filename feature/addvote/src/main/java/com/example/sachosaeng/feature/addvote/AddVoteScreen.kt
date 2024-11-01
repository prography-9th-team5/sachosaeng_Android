package com.example.sachosaeng.feature.addvote

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import coil.compose.AsyncImage
import com.example.sachosaeng.core.ui.component.CategoryCard
import com.example.sachosaeng.core.ui.component.CategoryCardWithoutBorder
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.component.topappbar.SachosaengDetailTopAppBar
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.ui.theme.Gs_G4
import com.sachosaeng.app.core.ui.theme.Gs_G5
import com.sachosaeng.app.core.ui.theme.Gs_White

@Composable
fun AddVoteScreen(
    navigateToBackStack: () -> Unit,
    viewModel: AddVoteViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()

    AddVoteScreen(
        state = state,
        onTitleChange = viewModel::onTitleChange,
        onCategorySelected = viewModel::onCategorySelected,
        navigateToBackStack = navigateToBackStack
    )
}

@Composable
internal fun AddVoteScreen(
    state: AddVoteUiState,
    onTitleChange: (String) -> Unit = {},
    onCategorySelected: (Category) -> Unit = {},
    navigateToBackStack: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .background(Gs_G2)
            .fillMaxSize()
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
                text = stringResource(id = R.string.add_vote_description),
                color = Gs_G5,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
            )
        }
        item {
            Title(resId = R.string.vote_title)
            DefaultTextField(value = state.title, onValueChange = onTitleChange)
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Title(
                    resId = R.string.vote_option
                )
                Image(
                    painter = painterResource(id = if (state.canMultipleCheck) R.drawable.ic_checked_circle else R.drawable.ic_unchecked_circle),
                    contentDescription = null
                )
            }
            OptionList(state.options)
        }
        item {
            Title(
                resId = R.string.vote_category
            )
            CategoryList(state.category, onCategorySelected)
        }
    }
}

@Composable
private fun OptionList(
    options: List<String> = emptyList()
) {
    options.forEach {
        DefaultSmallTextField(value = it, onValueChange = {})
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CategoryList(
    categories: List<Category> = emptyList(),
    onCategorySelected: (Category) -> Unit
) {
    FlowRow {
        categories.forEach {
            CategoryCardWithoutBorder(
                category = it,
                onCategoryClicked = onCategorySelected
            )
        }
    }
}

@Composable
private fun Title(
    resId: Int
) {
    Text(
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
            .padding(16.dp)
            .defaultMinSize(minHeight = 62.dp)
    )
}


@Composable
private fun DefaultSmallTextField(
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
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.padding(16.dp),
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