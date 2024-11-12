package com.sachosaeng.app.feature.home

import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.model.VoteInfo
import com.sachosaeng.app.core.model.VoteList
import com.sachosaeng.app.core.ui.UserType

data class HomeScreenUiState(
    val userType: UserType = UserType.NEW_EMPLOYEE,
    val mainVoteList: List<VoteList?> = emptyList(),
    val voteListWithCategory: VoteList? = VoteList(Category(), "", emptyList()),
    val hotVotes: VoteList = VoteList(Category(), "", emptyList()),
    val dailyVote: VoteInfo? = null,
    val allCategory: List<Category> = emptyList(),
    val myCategory: List<Category> = emptyList(),
    val modifyMyCategoryListVisibility: Boolean = false,
    val selectedCategory: Category = Category(),
    val isDailyVoteDialogOpen: Boolean = false
)