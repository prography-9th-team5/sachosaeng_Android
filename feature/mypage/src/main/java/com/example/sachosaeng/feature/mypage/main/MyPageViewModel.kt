package com.sachosaeng.app.feature.mypage.main

import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.util.ResourceProvider
import com.sachosaeng.app.core.usecase.auth.LogoutUsecase
import com.sachosaeng.app.core.usecase.user.DownloadProfileImageUseCase
import com.sachosaeng.app.core.usecase.user.GetMyInfoUsecase
import com.sachosaeng.app.core.util.manager.DeviceManager
import com.sachosaeng.app.core.util.manager.PackageManager
import com.sachosaeng.app.core.ui.R.string
import com.sachosaeng.app.feature.mypage.navigation.IS_LEVEL_UP
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
    val logoutUseCase: LogoutUsecase,
    val getMyInfoUseCase: GetMyInfoUsecase,
    val downloadProfileImageUseCase: DownloadProfileImageUseCase,
    private val packageManager: PackageManager,
    private val deviceManager: DeviceManager,
    val resourceProvider: ResourceProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel(), ContainerHost<MyPageUiState, MyPageSideEffect> {
    override val container: Container<MyPageUiState, MyPageSideEffect> = container(MyPageUiState())
    private val isLevelUp = savedStateHandle.get<Boolean>(IS_LEVEL_UP)

    init {
        if(isLevelUp == true) showLevelUpDialog()
    }

    private fun showLevelUpDialog() = intent {
        reduce {
            state.copy(
                levelUpDialogState = true
            )
        }
    }

    fun getUserInfo() = intent {
        getMyInfoUseCase().collectLatest { userInfo ->
            reduce {
                state.copy(
                    userInfo = userInfo,
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
        logoutUseCase().collectLatest {
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

    fun hideDownloadDialog() = intent {
        reduce {
            state.copy(
                downloadCompleteDialogState = false
            )
        }
    }

    fun downloadImage(bitmap: Bitmap) = intent {
        downloadProfileImageUseCase(bitmap).collectLatest {
            reduce {
                state.copy(downloadCompleteDialogState = true)
            }
        }
    }

    fun onShowAlert() = intent {
        postSideEffect(MyPageSideEffect.NavigateToAlertPage)
    }
}