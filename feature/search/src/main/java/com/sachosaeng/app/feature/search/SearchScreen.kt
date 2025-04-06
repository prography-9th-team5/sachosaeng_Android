package com.sachosaeng.app.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.model.VoteInfo
import com.sachosaeng.app.core.model.VoteList
import com.sachosaeng.app.core.ui.R.string
import com.sachosaeng.app.core.ui.component.CategoryTitleText
import com.sachosaeng.app.core.ui.component.SearchTopBar
import com.sachosaeng.app.core.ui.component.VoteColumnByCategory
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.core.ui.component.CategoryRow
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navigateToVoteCard: (Int, Boolean) -> Unit,
    navigateToMain: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val state = viewModel.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Gs_G2)
    ) {
        SearchTopBar(
            modifier = modifier.background(Gs_G2),
            placeholder = stringResource(id = string.search_placeholder),
            value = state.value.searchQuery,
            onValueChange = viewModel::onSearchQueryChanged,
            onSearch = viewModel::getSearchResults,
            onClear = viewModel::clearSearchQuery,
            navigateToBackStack = navigateToMain,
        )
        if (state.value.searchQuery.isEmpty()) {
            RecentSearchesScreen(
                modifier = modifier.padding(top = 60.dp),
                state = state.value,
                navigateToVoteCard = navigateToVoteCard
            )
        }
        else {
            SearchScreen(
                modifier = modifier,
                onCategoryClicked = viewModel::filterByCategory,
                state = state.value,
                navigateToVoteCard = navigateToVoteCard
            )
        }
    }
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchUiState,
    onCategoryClicked: (Category) -> Unit = {},
    navigateToVoteCard: (Int, Boolean) -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(top = 60.dp, start = 20.dp, end = 20.dp),
    ) {
        item {
            CategoryRow(
                modifyButtonVisibility = false,
                selectedCategory = state.selectedCategory,
                categories = state.allCategory,
                onCategoryClicked = onCategoryClicked,
            )
        }
        items(state.searchResults.size) {
            if (state.searchResults[it]?.voteInfo?.isNotEmpty() == true) {
                CategoryTitleText(
                    category = state.searchResults[it]!!.category
                )
                VoteColumnByCategory(
                    voteList = state.searchResults[it]!!.voteInfo,
                    onVoteClick = { navigateToVoteCard(it, false) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        navigateToVoteCard = { _, _ -> },
        state = SearchUiState(
            searchQuery = "Search Query",
            selectedCategory = Category(
                id = 1,
                name = "Category",
                color = "#FFFFFF",
                imageUrl = ""
            ),
            allCategory = listOf(
                Category(
                    id = 1,
                    name = "Category",
                    color = "#FFFFFF",
                    imageUrl = ""
                )
            ),
            searchResults = listOf(),
            recommendVotes = VoteList(
                category = Category(
                    id = 1,
                    name = "Category",
                    color = "#FFFFFF",
                    imageUrl = ""
                ),
                description = "Description",
                voteInfo = listOf(
                    VoteInfo(
                        id = 1,
                        title = "Vote Title",
                        category = Category(
                            id = 1,
                            name = "Category",
                            color = "#FFFFFF",
                            imageUrl = ""
                        ),
                        isClosed = false,
                        isVoted = false
                    )
                )
            )
        )
    )
}