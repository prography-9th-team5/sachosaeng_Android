package com.example.sachosaeng.feature.addvote

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.usecase.category.GetCategoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AddVoteViewModel @Inject constructor(
    val getCategoryListUseCase: GetCategoryListUseCase
) : ViewModel(),
    ContainerHost<AddVoteUiState, Unit> {
    override val container: Container<AddVoteUiState, Unit> =
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
    }

    fun onOptionSelected(option: String) = intent {
        reduce {
            state.copy(options = state.options + option)
        }
    }

    fun onCategorySelected(category: Category) = intent {
        reduce {
            if (state.selectedCategory.contains(category)) {
                state.copy(selectedCategory = state.selectedCategory - category)
            } else state.copy(selectedCategory = state.selectedCategory + category)
        }
    }
}

