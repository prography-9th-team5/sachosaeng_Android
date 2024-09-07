package com.example.sachosaeng.feature.home

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.model.VoteInfo
import com.example.sachosaeng.core.model.VoteList
import com.example.sachosaeng.core.ui.UserType

data class HomeScreenUiState(
    val userType: UserType = UserType.NEW_EMPLOYEE,
    val voteList: List<VoteList> = emptyList(),
    val hotVotes: List<VoteList> = emptyList(),
    val dailyVote: VoteInfo? = null,
    val allCategory: List<Category> = emptyList(),
    val myCategory: List<Category> = emptyList(),
    val modifyMyCategoryListVisibility: Boolean = false,
    val selectedCategory: Category? = null,
    val isSelectCategoryModalOpen: Boolean = false
)