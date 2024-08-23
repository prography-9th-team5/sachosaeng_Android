package com.example.sachosaeng.feature.signup.selectcategory

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.domain.model.Category
import com.example.sachosaeng.core.usecase.category.GetCategoryListUsecase
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
class SelectCategoryViewModel @Inject constructor(
    val getAllCategoryListUsecase: GetCategoryListUsecase
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

    fun selectCategory(category: Category) = intent {
        reduce { state.copy(selectedCategoryList = state.selectedCategoryList + category) }
    }

    fun skipSelectCategory() = intent {
        reduce { state.copy(selectedCategoryList = emptyList()) }
        postSideEffect(SelectCategorySideEffect.NavigateToNextStep)
    }
}

sealed class SelectCategorySideEffect {
    object NavigateToNextStep : SelectCategorySideEffect()
}