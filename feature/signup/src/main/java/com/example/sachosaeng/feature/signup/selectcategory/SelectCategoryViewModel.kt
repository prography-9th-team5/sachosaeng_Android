package com.example.sachosaeng.feature.signup.selectcategory

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.usecase.auth.GetEmailUsecase
import com.example.sachosaeng.core.usecase.auth.JoinUseCase
import com.example.sachosaeng.core.usecase.category.GetCategoryListUsecase
import com.example.sachosaeng.core.usecase.category.SetMyCategoryListUseCase
import com.example.sachosaeng.core.usecase.user.GetUserTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
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
    val getAllCategoryListUsecase: GetCategoryListUsecase,
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
        setMyCategoryListUseCase(newSelectecCategoryList).collectLatest {
            reduce { state.copy(selectedCategoryList = newSelectecCategoryList) }
        }
    }

    private fun unselectCategory(category: Category) = intent {
        reduce { state.copy(selectedCategoryList = state.selectedCategoryList - category) }
    }

    fun skipSelectCategory() = intent {
        setMyCategoryListUseCase(emptyList()).collectLatest {
            postSideEffect(SelectCategorySideEffect.NavigateToNextStep)
        }
    }

    fun join() = intent {
        val email = getEmailUseCase().first()
        val userType = getLocalUserTypeUseCase().first()
        runCatching {
            joinUseCase(email = email, userType = userType).collectLatest {}
        }.onFailure {
            postSideEffect(SelectCategorySideEffect.ShowError(it.message ?: "unknown"))
        }.onSuccess {
            postSideEffect(SelectCategorySideEffect.NavigateToNextStep)
        }
    }
}

sealed class SelectCategorySideEffect {
    object NavigateToNextStep : SelectCategorySideEffect()
    data class ShowError(val message: String) : SelectCategorySideEffect()
}