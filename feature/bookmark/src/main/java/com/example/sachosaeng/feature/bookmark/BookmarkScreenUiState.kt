package com.sachosaeng.app.feature.bookmark

import com.sachosaeng.app.core.model.Bookmark
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.ui.UserType

data class BookmarkScreenUiState(
    val userType: UserType = UserType.NEW_EMPLOYEE,
    val selectedCategory: Category? = null,
    val allCategory: List<Category> = emptyList(),
    val bookmarkedVoteList: List<Bookmark> = emptyList(),
    val bookmarkedArticleList: List<Bookmark> = emptyList(),
    val isModifyMode : Boolean = false,
    val selectedForModifyBookmarkList: List<Bookmark> = emptyList(),
    val selectedForModifyBookmarkedArticleList: List<Bookmark> = emptyList(),
)