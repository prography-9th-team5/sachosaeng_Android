package com.example.sachosaeng.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.usecase.category.GetCategoryListWithAllIconUseCase
import com.example.sachosaeng.core.usecase.category.GetMyCategoryListUsecase
import com.example.sachosaeng.core.usecase.category.SetMyCategoryListUseCase
import com.example.sachosaeng.core.usecase.user.GetMyInfoUsecase
import com.example.sachosaeng.core.usecase.user.GetUserTypeUseCase
import com.example.sachosaeng.core.usecase.vote.GetDailyVoteUsecase
import com.example.sachosaeng.core.usecase.vote.GetHotVoteUsecase
import com.example.sachosaeng.core.usecase.vote.GetVoteByCategoryUsecase
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
class HomeViewModel @Inject constructor(
    private val getDailyVoteUsecase: GetDailyVoteUsecase,
    private val getHotVoteUsecase: GetHotVoteUsecase,
    private val getVoteByCategoryUsecase: GetVoteByCategoryUsecase,
    private val getMyInfoUsecase: GetMyInfoUsecase,
    private val getCategoryListWithAllIconUseCase: GetCategoryListWithAllIconUseCase,
    private val getMyCategoryListUsecase: GetMyCategoryListUsecase,
    private val setMyCategoryListUseCase: SetMyCategoryListUseCase
) : ViewModel(), ContainerHost<HomeScreenUiState, HomeSideEffect> {
    override val container: Container<HomeScreenUiState, HomeSideEffect> =
        container(HomeScreenUiState())

    init {
        getDailyVote()
        getHotVotes()
        getUserInfo()
        getCategoryList()
        getMyCategoryListAndVoteList()
    }

    private fun getUserInfo() = intent {
        getMyInfoUsecase().collectLatest {
            reduce { state.copy(userType = UserType.getType(it.userType) ?: UserType.NEW_EMPLOYEE) }
        }
    }

    private fun getVoteByMyCategory() = intent {
        state.myCategory.forEach {
            getVoteByCategoryUsecase(it.id).collectLatest {
                reduce {
                    state.copy(
                        voteListWithCategory = state.voteListWithCategory.plus(it)
                    )
                }
            }
        }
    }

    fun onSelectCategory(category: Category) = intent {
        getVoteByCategoryUsecase(category.id).collectLatest {
            reduce {
                state.copy(
                    selectedCategory = category,
                    voteListWithCategory = listOf(it)
                )
            }
        }
    }

    fun onSelectFavoriteCategory(category: Category) = intent {
        val currentMyCategory = state.myCategory
        reduce {
            if (currentMyCategory.contains(category)) state.copy(myCategory = currentMyCategory.minus(category))
            else state.copy(myCategory = currentMyCategory.plus(category))
        }
    }

    fun onModifyMyCategory() = intent {
        reduce { state.copy(modifyMyCategoryListVisibility = true) }
    }

    fun onModifyComplete() = intent {
        setMyCategoryListUseCase(state.myCategory).collectLatest {
            getMyCategoryListAndVoteList()
        }
    }

    private fun getDailyVote() = intent {
        getDailyVoteUsecase().collectLatest {
            reduce {
                state.copy(
                    dailyVote = it, isDailyVoteDialogOpen = it?.isVoted == false
                )
            }
        }
    }

    private fun getHotVotes() = intent {
        getHotVoteUsecase().collectLatest { list ->
            list?.let {
                reduce {
                    state.copy(
                        hotVotes = it,
                    )
                }
            }
        }
    }

    private fun getCategoryList() = intent {
        getCategoryListWithAllIconUseCase().collectLatest {
            reduce { state.copy(allCategory = it) }
        }
    }

    private fun getMyCategoryListAndVoteList() = intent {
        getMyCategoryListUsecase().collectLatest {
            reduce { state.copy(myCategory = it, modifyMyCategoryListVisibility = false) }
        }.also {
            getVoteByMyCategory()
        }
    }

    fun onDailyVoteDialogConfirmClicked() = intent {
        reduce { state.copy(isDailyVoteDialogOpen = false) }.also {
            state.dailyVote?.let {
                postSideEffect(HomeSideEffect.NavigateToVoteDetail(state.dailyVote!!.id, true))
            }
        }
    }
}

sealed class HomeSideEffect {
    data class NavigateToVoteDetail(val voteId: Int, val isDailyVote: Boolean) : HomeSideEffect()
}