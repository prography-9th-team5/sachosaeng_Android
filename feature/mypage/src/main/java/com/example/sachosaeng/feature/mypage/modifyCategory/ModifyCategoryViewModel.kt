package com.example.sachosaeng.feature.mypage.modifyCategory

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.R.string
import com.example.sachosaeng.core.ui.ResourceProvider
import com.example.sachosaeng.core.usecase.category.GetCategoryListUseCase
import com.example.sachosaeng.core.usecase.category.GetMyCategoryListUsecase
import com.example.sachosaeng.core.usecase.category.SetMyCategoryListUseCase
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
class ModifyCategoryViewModel @Inject constructor(
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val getMyCategoryListUsecase: GetMyCategoryListUsecase,
    private val setMyCategoryListUseCase: SetMyCategoryListUseCase,
    private val resourceProvider: ResourceProvider
) : ViewModel(), ContainerHost<ModifyCategoryUiState, ModifyCategorySideEffect> {
    override val container: Container<ModifyCategoryUiState, ModifyCategorySideEffect> =
        container(ModifyCategoryUiState())

    init {
        getMyCategoryList()
    }

    private fun getCategoryList() = intent {
        getCategoryListUseCase().collectLatest {
            reduce { state.copy(visibleList = it) }
        }
    }

    private fun getMyCategoryList() = intent {
        getMyCategoryListUsecase().collectLatest {
            reduce {
                state.copy(
                    selectedList = it,
                    visibleList = it,
                    modifyButtonVisibility = true
                )
            }
        }
    }

    fun selectCategory(category: Category) = intent {
        when (state.selectedList.contains(category)) {
            true -> reduce { state.copy(selectedList = state.selectedList - category) }
            false -> reduce { state.copy(selectedList = state.selectedList + category) }
        }
    }

    fun modifyMyCategory() = intent {
        getCategoryList()
        reduce { state.copy(modifyButtonVisibility = !state.modifyButtonVisibility) }
    }

    fun modifyComplete() = intent {
        setMyCategoryListUseCase(state.selectedList).collect {
            postSideEffect(ModifyCategorySideEffect.ShowSnackBar(resourceProvider.getString(string.saved_snackbar)))
        }
        getMyCategoryList()
    }
}

sealed class ModifyCategorySideEffect {
    data class ShowSnackBar(val message: String) : ModifyCategorySideEffect()
}
