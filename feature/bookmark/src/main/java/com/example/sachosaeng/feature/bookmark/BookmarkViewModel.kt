package com.example.sachosaeng.feature.bookmark

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.model.Bookmark
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.R.string.all_category_icon_text
import com.example.sachosaeng.core.ui.ResourceProvider
import com.example.sachosaeng.core.usecase.bookmark.DeleteBookmarksUseCase
import com.example.sachosaeng.core.usecase.bookmark.GetBookmarkListUseCase
import com.example.sachosaeng.core.usecase.category.GetCategoryListUseCase
import com.example.sachosaeng.core.util.constant.IntConstant.ALL_CATEGORY_ID
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
    private val deleteBookmarkUseCase: DeleteBookmarksUseCase
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
        when (state.selectedForModifyBookmarkList.contains(bookmark)) {
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

    fun deleteSelectedBookmarks() = intent {
        deleteBookmarkUseCase(state.selectedForModifyBookmarkList).collectLatest { getBookmarkListByCategory() }
    }

    fun categoryClicked(category: Category) = intent {
        reduce {
            state.copy(
                selectedCategory = category
            )
        }.also {
            if(category.id == ALL_CATEGORY_ID) getAllBookmarkList()
            else getBookmarkListByCategory()
        }
    }

    private fun getBookmarkListByCategory() = intent {
        getBookmarkListByCategoryUseCase(state.selectedCategory).collectLatest { bookmarkList ->
            reduce {
                state.copy(
                    bookmarkList = bookmarkList,
                    isModifyMode = false,
                )
            }
        }
    }

    private fun getAllBookmarkList() = intent {
        getBookmarkListByCategoryUseCase().collectLatest { bookmarkList ->
            reduce {
                state.copy(
                    bookmarkList = bookmarkList
                )
            }
        }
    }
}