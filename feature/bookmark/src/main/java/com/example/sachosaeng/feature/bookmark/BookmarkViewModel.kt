package com.example.sachosaeng.feature.bookmark

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.model.Bookmark
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.R.string.all_category_icon_text
import com.example.sachosaeng.core.ui.ResourceProvider
import com.example.sachosaeng.core.usecase.bookmark.GetBookmarkListUseCase
import com.example.sachosaeng.core.usecase.category.GetCategoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val stringResourceProvider: ResourceProvider,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val getBookmarkListByCategoryUseCase: GetBookmarkListUseCase,
) : ViewModel(), ContainerHost<BookmarkScreenUiState, Unit> {
    override val container: Container<BookmarkScreenUiState, Unit> =
        container(BookmarkScreenUiState())

    init {
        getAllCategoryList()
        getAllBookmarkList()
    }

    private fun getAllCategoryList() = intent {
        getCategoryListUseCase().collectLatest { allCategoryList ->
            val newList =
                listOf(Category(name = stringResourceProvider.getString(all_category_icon_text))) + allCategoryList
            reduce {
                state.copy(
                    allCategory = newList
                )
            }
        }
    }

    fun modifyBookmark() = intent {
        reduce {
            state.copy(
                isModifyMode = !state.isModifyMode
            )
        }
    }

    fun selectModifyBookmark(bookmark: Bookmark) = intent {
        when(state.selectedForModifyBookmarkList.contains(bookmark)){
            true -> reduce {
                state.copy(
                    selectedForModifyBookmarkList = state.selectedForModifyBookmarkList - bookmark
                )
            }
            false -> reduce {
                state.copy(
                    selectedForModifyBookmarkList = state.selectedForModifyBookmarkList + bookmark
                )
            }
        }
    }

    fun categoryClicked(category: Category) = intent {
        getBookmarkListByCategoryUseCase(category = category).collectLatest { bookmarkList ->
            reduce {
                state.copy(
                    bookmarkList = bookmarkList,
                    selectedCategory = category
                )
            }
        }
    }

    private fun getAllBookmarkList() = intent {
        getBookmarkListByCategoryUseCase(category = Category(id = 1)).collectLatest { bookmarkList ->
            reduce {
                state.copy(
                    bookmarkList = bookmarkList
                )
            }
        }
    }
}