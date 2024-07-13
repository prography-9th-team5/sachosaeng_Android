package com.example.sachosaeng.feature.mypage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(): ViewModel(), ContainerHost <MyPageUiState, Unit>{
    override val container: Container<MyPageUiState, Unit> = container(MyPageUiState())

    fun getUserInfo() = intent {
        reduce {
            state.copy(
                levelText = "레벨 1",
                userName = "사초생"
            )
        }
    }

    fun withdraw() = intent {
        reduce {
            state.copy(
                levelText = "",
                userName = ""
            )
        }
    }
}