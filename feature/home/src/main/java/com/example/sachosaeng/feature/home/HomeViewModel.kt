package com.example.sachosaeng.feature.home

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.model.VoteList
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.usecase.user.GetUserTypeUseCase
import com.example.sachosaeng.core.usecase.vote.GetDailyVoteUsecase
import com.example.sachosaeng.core.usecase.vote.GetHotVoteUsecase
import com.example.sachosaeng.core.usecase.vote.GetVoteByCategoryUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDailyVoteUsecase: GetDailyVoteUsecase,
    private val getHotVoteUsecase: GetHotVoteUsecase,
    private val getVoteByCategoryUsecase: GetVoteByCategoryUsecase,
    private val getUserTypeUseCase: GetUserTypeUseCase
) : ViewModel(), ContainerHost<HomeScreenUiState, Unit> {
    override val container: Container<HomeScreenUiState, Unit> =
        container(HomeScreenUiState.createDefault())

    init {
        getDailyVote()
        getHotVotes()
        getVoteByCategory(0)
        getUserInfo()
    }

    fun getUserInfo() = intent {
        getUserTypeUseCase().collectLatest {
            reduce { state.copy(userType = UserType.getType(it) ?: UserType.NEW_EMPLOYEE) }
        }
    }

    fun onSelectCategory(category: Category) = intent {
        reduce { state.copy(selectedCategory = category) }
    }

    private fun getDailyVote() = intent {
        getDailyVoteUsecase().collectLatest {
            reduce { state.copy(dailyVote = it) }
        }
    }

    private fun getHotVotes() = intent {
        getHotVoteUsecase().collectLatest { list ->
            list?.let { reduce { state.copy(hotVotes = listOf(it)) } }
        }
    }

    private fun getVoteByCategory(categoryId: Int) = intent {
        getVoteByCategoryUsecase(id = categoryId).collectLatest { list ->
            list?.let { reduce { state.copy(voteList = listOf(it)) } }
        }
    }
}