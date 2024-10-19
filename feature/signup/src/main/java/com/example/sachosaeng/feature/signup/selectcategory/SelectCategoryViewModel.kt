package com.sachosaeng.app.feature.signup.selectcategory

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.usecase.auth.GetEmailUsecase
import com.sachosaeng.app.core.usecase.auth.JoinUseCase
import com.sachosaeng.app.core.usecase.category.GetCategoryListUseCase
import com.sachosaeng.app.core.usecase.category.SetMyCategoryListUseCase
import com.sachosaeng.app.core.usecase.user.GetUserTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SelectCategoryViewModel @Inject constructor(
    val getEmailUseCase: GetEmailUsecase,
    val getLocalUserTypeUseCase: GetUserTypeUseCase,
    val getAllCategoryListUsecase: GetCategoryListUseCase,
    val setMyCategoryListUseCase: SetMyCategoryListUseCase,
    val joinUseCase: JoinUseCase
) : ViewModel(),
    ContainerHost<SelectCategoryUiState, SelectCategorySideEffect> {
    override val container: Container<SelectCategoryUiState, SelectCategorySideEffect> =
        container(SelectCategoryUiState())

    init {
        getAllCategories()
    }

    private fun getAllCategories() = intent {
        getAllCategoryListUsecase().collectLatest {
            reduce { state.copy(categoryList = it) }
        }
    }

    fun onClickCategory(category: Category) = intent {
        when (state.selectedCategoryList.contains(category)) {
            true -> unselectCategory(category)
            false -> selectCategory(category)
        }
        checkIsAnyCategorySelected()
    }

    private fun checkIsAnyCategorySelected() = intent {
        reduce {
            state.copy(isAnyCategorySelected = state.selectedCategoryList.isNotEmpty())
        }
    }

    private fun selectCategory(category: Category) = intent {
        val newSelectecCategoryList = state.selectedCategoryList + category
        reduce { state.copy(selectedCategoryList = newSelectecCategoryList) }
        checkIsAnyCategorySelected()
    }

    private fun unselectCategory(category: Category) = intent {
        reduce { state.copy(selectedCategoryList = state.selectedCategoryList - category) }
    }

    fun skipSelectCategory() = intent {
        postSideEffect(SelectCategorySideEffect.NavigateToNextStep)
    }

    fun join() = intent {
        val email = getEmailUseCase().firstOrNull() ?: ""
        val userType = getLocalUserTypeUseCase().firstOrNull() ?: ""
        runCatching {
            joinUseCase(email = email, userType = userType).collectLatest {
                setMyCategoryListUseCase(state.selectedCategoryList).collectLatest {
                    postSideEffect(SelectCategorySideEffect.NavigateToNextStep)
                }
            }
        }.onFailure {
            postSideEffect(SelectCategorySideEffect.ShowError(it.message ?: "unknown"))
        }
    }
}

sealed class SelectCategorySideEffect {
    object NavigateToNextStep : SelectCategorySideEffect()
    data class ShowError(val message: String) : SelectCategorySideEffect()
}