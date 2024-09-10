package com.example.sachosaeng.feature.bookmark

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.model.VoteInfo
import com.example.sachosaeng.core.model.VoteList
import com.example.sachosaeng.core.ui.UserType

data class BookmarkScreenUiState(
    val userType: UserType = UserType.NEW_EMPLOYEE,
)