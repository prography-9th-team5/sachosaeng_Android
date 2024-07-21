package com.example.sachosaeng.feature.signup.selectcategory

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.domain.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SelectCategoryViewModel @Inject constructor() : ViewModel(),
    ContainerHost<SelectCategoryUiState, Unit> {
    override val container: Container<SelectCategoryUiState, Unit> =
        container(SelectCategoryUiState())

    fun selectCategory(category: Category) = intent {
        reduce { state.copy(selectedCategoryList = state.selectedCategoryList + category) }
    }

    fun skipSelectCategory() = intent {
        reduce { state.copy(selectedCategoryList = emptyList()) }
    }
}