package com.example.sachosaeng.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.usecase.category.GetCategoryListWithAllIconUseCase
import com.example.sachosaeng.core.usecase.category.GetMyCategoryListUsecase
import com.example.sachosaeng.core.usecase.category.SetMyCategoryListUseCase
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
    private val getUserTypeUseCase: GetUserTypeUseCase,
    private val getCategoryListWithAllIconUseCase: GetCategoryListWithAllIconUseCase,
    private val getMyCategoryListUsecase: GetMyCategoryListUsecase,
    private val setMyCategoryListUseCase: SetMyCategoryListUseCase
) : ViewModel(), ContainerHost<HomeScreenUiState, Unit> {
    override val container: Container<HomeScreenUiState, Unit> =
        container(HomeScreenUiState())

    init {
        getDailyVote()
        getHotVotes()
        getVoteByCategory(0)
        getUserInfo()
        getCategoryList()
        getMyCategoryList()
    }

    fun getUserInfo() = intent {
        getUserTypeUseCase().collectLatest {
            reduce { state.copy(userType = UserType.getType(it) ?: UserType.NEW_EMPLOYEE) }
        }
    }

    fun bottomSheetClose() = intent {
        reduce { state.copy(isSelectCategoryModalOpen = false) }
    }

    fun bottomSheetOpen() = intent {
        reduce { state.copy(isSelectCategoryModalOpen = true) }
    }

    fun onSelectCategory(category: Category) = intent {
        reduce { state.copy(selectedCategory = category) }
    }

    fun onSelectFavoriteCategory(category: Category) = intent {
        val currentMyCategory = state.myCategory
        reduce { state.copy(myCategory = currentMyCategory.plus(category)) }
    }

    fun onModifyMyCategory() = intent {
        reduce { state.copy(modifyMyCategoryListVisibility = true) }
    }

    fun onModifyComplete() = intent {
        setMyCategoryListUseCase(state.myCategory)
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

    private fun getCategoryList() = intent {
        getCategoryListWithAllIconUseCase().collectLatest {
            reduce { state.copy(allCategory = it) }
        }
    }

    private fun getMyCategoryList() = intent {
        getMyCategoryListUsecase().collectLatest {
            reduce { state.copy(myCategory = it) }
        }
    }
}