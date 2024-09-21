package com.example.sachosaeng.feature.bookmark

import com.example.sachosaeng.core.model.Bookmark
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.UserType

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