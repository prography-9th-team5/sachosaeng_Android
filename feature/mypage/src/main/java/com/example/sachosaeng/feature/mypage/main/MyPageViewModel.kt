package com.sachosaeng.app.feature.mypage.main

import androidx.lifecycle.ViewModel
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.ui.UserType
import com.sachosaeng.app.core.usecase.auth.LogoutUsecase
import com.sachosaeng.app.core.usecase.category.GetCategoryListWithAllIconUseCase
import com.sachosaeng.app.core.usecase.category.GetMyCategoryListUsecase
import com.sachosaeng.app.core.usecase.category.SetMyCategoryListUseCase
import com.sachosaeng.app.core.usecase.user.GetMyInfoUsecase
import com.sachosaeng.app.core.util.manager.DeviceManager
import com.sachosaeng.app.core.util.manager.PackageManager
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
class MyPageViewModel @Inject constructor(
    val logoutUsecase: LogoutUsecase,
    val getMyInfoUseCase: GetMyInfoUsecase,
    val packageManager: PackageManager,
    val deviceManager: DeviceManager
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

    fun logout() = intent {
        logoutUsecase().collectLatest {
            hideLogoutDialog()
            deviceManager.finishApp()
        }
    }

    fun hideLogoutDialog() = intent {
        reduce {
            state.copy(
                logoutDialogState = false
            )
        }
    }
}