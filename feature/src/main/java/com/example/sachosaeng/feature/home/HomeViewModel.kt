package com.example.sachosaeng.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sachosaeng.core.domain.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(), ContainerHost<HomeScreenUiState, Unit> {
    override val container: Container<HomeScreenUiState, Unit> =
        container(HomeScreenUiState.createDefault())

    init {
        Log.e("1111111", "오는거 맞나..")
    }

    fun initHomeScreen() = intent {
        viewModelScope.launch {
            reduce { state }
        }
    }

    fun onSelectCategory(category: Category) = intent {
        viewModelScope.launch {
            reduce { state.copy(selectedCategory = category) }
        }
    }
}