package com.example.sachosaeng.feature.home

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.model.VoteInfo
import com.example.sachosaeng.core.model.VoteList
import com.example.sachosaeng.core.ui.UserType

data class HomeScreenUiState(
    val userType: UserType,
    val voteList: List<VoteList>,
    val hotVotes: List<VoteList>,
    val dailyVote: VoteInfo? = null,
    val allCategory: List<Category>,
    val myCategory: List<Category>,
    val selectedCategory: Category? = null
) {
    companion object {
        fun createDefault(): HomeScreenUiState {
            return HomeScreenUiState(
                userType = UserType.NEW_EMPLOYEE,
                dailyVote = null,
                hotVotes = listOf(
                    VoteList(
                        category = Category(0, "#000000", "hot"),
                        voteInfo = emptyList()
                    )
                ),
                voteList = emptyList(),
                allCategory = emptyList(),
                myCategory = emptyList(),
            )
        }
    }
}