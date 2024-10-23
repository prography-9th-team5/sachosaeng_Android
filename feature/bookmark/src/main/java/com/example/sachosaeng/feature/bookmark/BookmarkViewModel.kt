package com.sachosaeng.app.feature.bookmark

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.util.ResourceProvider
import com.sachosaeng.app.core.model.Bookmark
import com.sachosaeng.app.core.model.BookmarkType
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.usecase.bookmark.DeleteArticleBookmarksUseCase
import com.sachosaeng.app.core.usecase.bookmark.DeleteVoteBookmarksUseCase
import com.sachosaeng.app.core.usecase.bookmark.GetBookmarkListUseCase
import com.sachosaeng.app.core.usecase.bookmark.GetBookmarkedArticleListUseCase
import com.sachosaeng.app.core.usecase.category.GetCategoryListUseCase
import com.sachosaeng.app.core.util.constant.IntConstant.ALL_CATEGORY_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val stringResourceProvider: ResourceProvider,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val getBookmarkListByCategoryUseCase: GetBookmarkListUseCase,
    private val getBookmarkedArticleListUseCase: GetBookmarkedArticleListUseCase,
    private val deleteBookmarkUseCase: DeleteVoteBookmarksUseCase,
    private val deleteArticleBookmarksUseCase: DeleteArticleBookmarksUseCase,
    private val resourceProvider: ResourceProvider
) : ViewModel(), ContainerHost<BookmarkScreenUiState, BookmarkSideEffect> {
    override val container: Container<BookmarkScreenUiState, BookmarkSideEffect> =
        container(BookmarkScreenUiState())

    init {
        getAllCategoryList()
        getAllBookmarkList()
        getAllBookmarkedArticleList()
    }

    private fun getAllCategoryList() = intent {
        getCategoryListUseCase().collectLatest { allCategoryList ->
            val newList =
                listOf(
                    Category(
                        id = ALL_CATEGORY_ID,
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
        deleteBookmarkUseCase(state.selectedForModifyBookmarkList).collectLatest {
            postSideEffect(BookmarkSideEffect.ShowSnackBar(resourceProvider.getString(R.string.bookmark_modify_complete)))
            getBookmarkListByCategory()
        }
    }


    fun deleteSelectedArticles() = intent {
        deleteArticleBookmarksUseCase(state.selectedForModifyBookmarkList).collectLatest {
            postSideEffect(BookmarkSideEffect.ShowSnackBar(resourceProvider.getString(R.string.bookmark_modify_complete)))
            getBookmarkedArticleListByCategory()
        }
    }

    fun categoryClicked(category: Category) = intent {
        reduce {
            state.copy(
                selectedCategory = category
            )
        }.also {
            if (category.id == ALL_CATEGORY_ID) {
                getAllBookmarkList()
                getAllBookmarkedArticleList()
            } else {
                getBookmarkListByCategory()
                getBookmarkedArticleListByCategory()
            }
        }
    }

    private fun getBookmarkListByCategory() = intent {
        getBookmarkListByCategoryUseCase(state.selectedCategory).collectLatest { bookmarkList ->
            reduce {
                state.copy(
                    bookmarkedVoteList = bookmarkList,
                    isModifyMode = false,
                )
            }
        }
    }

    private fun getAllBookmarkList() = intent {
        getBookmarkListByCategoryUseCase().collectLatest { bookmarkList ->
            reduce {
                state.copy(
                    bookmarkedVoteList = bookmarkList
                )
            }
        }
    }

    private fun getAllBookmarkedArticleList() = intent {
        getBookmarkedArticleListUseCase().collectLatest { bookmarkList ->
            reduce {
                state.copy(
                    bookmarkedArticleList = bookmarkList
                )
            }
        }
    }

    private fun getBookmarkedArticleListByCategory() = intent {
        getBookmarkedArticleListUseCase(state.selectedCategory).collectLatest { bookmarkList ->
            reduce {
                state.copy(
                    bookmarkedArticleList = bookmarkList
                )
            }
        }
    }
}

sealed class BookmarkSideEffect {
    data class ShowSnackBar(val message: String) : BookmarkSideEffect()
}