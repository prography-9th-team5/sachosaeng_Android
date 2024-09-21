package com.example.sachosaeng.feature.mypage.main

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.usecase.user.GetMyInfoUsecase
import com.example.sachosaeng.core.util.manager.PackageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    val getMyInfoUseCase: GetMyInfoUsecase,
    val packageManager: PackageManager
): ViewModel(), ContainerHost <MyPageUiState, Unit>{
    override val container: Container<MyPageUiState, Unit> = container(MyPageUiState())

    fun getUserInfo() = intent {
        getMyInfoUseCase().collectLatest { userInfo ->
            reduce {
                state.copy(
                    levelText = "레벨 1",
                    userName = userInfo.name,
                    userType = UserType.getType(userInfo.userType) ?: UserType.OTHER,
                    versionInfo = packageManager.getVersionName()
                )
            }
        }
    }

    fun showLogoutDialog() = intent {
        reduce {
            state.copy(
                logoutDialogState = true
            )
        }
    }

    fun hideWithdrawDialog() = intent {
        reduce {
            state.copy(
                logoutDialogState = false
            )
        }
    }
}
