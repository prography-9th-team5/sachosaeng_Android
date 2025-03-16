package com.sachosaeng.app.feature.search

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.usecase.search.GetRecentSearchesUseCase
import com.example.sachosaeng.core.usecase.search.GetSearchResultUseCase
import com.example.sachosaeng.core.usecase.search.SetRecentSearchesUseCase
import com.example.sachosaeng.core.util.ResourceProvider
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.model.VoteList
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.usecase.category.GetCategoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val stringResourceProvider: ResourceProvider,
    val getSearchResultsUseCase: GetSearchResultUseCase,
    val getRecentSearchesUseCase: GetRecentSearchesUseCase,
    val setRecentSearchesUseCase: SetRecentSearchesUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase,
) : ViewModel(), ContainerHost<SearchUiState, Unit> {
    override val container: Container<SearchUiState, Unit> =
        container(SearchUiState())

    init {
        getRecentSearches()
        getAllCategoryList()
    }

    private fun getAllCategoryList() = intent {
        getCategoryListUseCase().collectLatest { allCategoryList ->
            val newList =
                listOf(
                    Category(
                        id = -1,
                        name = stringResourceProvider.getString(R.string.all_category_icon_text)
                    )
                ) + allCategoryList
            reduce {
                state.copy(
                    allCategory = newList
                )
            }
        }
    }

    fun getSearchResults(query: String) = intent {
        getSearchResultsUseCase(query)?.collectLatest { searchResults ->
            reduce {
                state.copy(searchResults = searchResults)
            }.run {
                if(query.isEmpty()) getRecentSearches()
                else setRecentSearchesUseCase(query)
            }
        }
    }

    fun onSearchQueryChanged(query: String) = blockingIntent {
        reduce {
            state.copy(searchQuery = query)
        }
    }

    fun clearSearchQuery() = intent {
        reduce {
            state.copy(searchQuery = "")
        }
    }

    fun filterByCategory(category: Category) = intent {
        reduce {
            state.copy(
                selectedCategory = category,
                searchResults = state.searchResults.filter { it?.category == category })
        }
    }

    private fun getRecentSearches() = intent {
        getRecentSearchesUseCase()?.collectLatest { recentSearches ->
            reduce {
                state.copy(recentSearches = recentSearches)
            }
        }
    }
}

data class SearchUiState(
    val searchQuery: String = "",
    val selectedCategory: Category = Category(),
    val allCategory: List<Category> = emptyList(),
    val searchResults: List<VoteList?> = listOf(VoteList(Category(), "", emptyList())),
    val recentSearches: List<String?> = emptyList(),
    val recommendVotes: VoteList = VoteList(Category(), "", emptyList()),
)