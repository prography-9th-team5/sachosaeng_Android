package com.example.sachosaeng.feature.bookmark

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.UserType
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
class BookmarkViewModel @Inject constructor(
    private val getMyCategoryListUseCase: GetMyCategoryListUsecase,
) : ViewModel(), ContainerHost<BookmarkScreenUiState, Unit> {
    override val container: Container<BookmarkScreenUiState, Unit> =
        container(BookmarkScreenUiState())

    init {
        getMyCategoryList()
    }

    private fun getMyCategoryList() = intent {
        getMyCategoryListUseCase().collectLatest { myCategoryList ->
            reduce {
                state.copy(
                    myCategory = myCategoryList
                )
            }
        }
    }
}