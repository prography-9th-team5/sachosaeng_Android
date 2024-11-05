package com.example.sachosaeng.feature.addvote

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.usecase.vote.AddVoteUseCase
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.usecase.category.GetCategoryListUseCase
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
class AddVoteViewModel @Inject constructor(
    val getCategoryListUseCase: GetCategoryListUseCase,
    val addVoteUseCase: AddVoteUseCase
) : ViewModel(),
    ContainerHost<AddVoteUiState, AddVoteSideEffect> {
    override val container: Container<AddVoteUiState, AddVoteSideEffect> =
        container(AddVoteUiState())

    init {
        getCategoryList()
    }

    private fun getCategoryList() = intent {
        getCategoryListUseCase().collectLatest {
            reduce {
                state.copy(category = it)
            }
        }
    }

    fun onTitleChange(title: String) = intent {
        reduce {
            state.copy(title = title)
        }
        checkIsButtonEnabled()
    }

    fun onOptionSelected(option: String, index: Int) = intent {
        reduce {
            val newOptionList = state.options.toMutableList()
            newOptionList[index] = option
            state.copy(options = newOptionList)
        }
        checkIsButtonEnabled()
    }

    fun onCategorySelected(category: Category) = intent {
        reduce {
            state.copy(selectedCategory = category)
        }
        checkIsButtonEnabled()
    }

    fun onAddVoteButtonClicked() = intent {
        addVoteUseCase(
            title = state.title,
            options = state.options,
            categoryId = state.selectedCategory!!.id,
            isMultipleChoiceAllowed = state.canMultipleCheck
        ).collectLatest {
            postSideEffect(AddVoteSideEffect.ShowSnackBar)
        }
    }

    fun onChangeMultipleCheck() = intent {
        reduce {
            state.copy(canMultipleCheck = !state.canMultipleCheck)
        }
        checkIsButtonEnabled()
    }

    private fun checkIsButtonEnabled() = intent {
        reduce {
            state.copy(
                isAddButtonEnabled = state.title.isNotBlank() &&
                        state.options.any { it.isNotBlank() } &&
                        state.selectedCategory != null
            )
        }
    }
}

sealed class AddVoteSideEffect {
    data object ShowSnackBar : AddVoteSideEffect()
}

