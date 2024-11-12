package com.sachosaeng.app.main

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.util.ErrorNotifier
import com.example.sachosaeng.core.util.ResourceProvider
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.util.constant.NavigationConstant.Main.ROUTE_MAIN
import com.sachosaeng.app.core.util.manager.DeviceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    val deviceManager: DeviceManager,
    val resourceProvider: ResourceProvider,
) : ViewModel(), ContainerHost<AppUiState, AppSideEffect> {
    override val container: Container<AppUiState, AppSideEffect> =
        container(AppUiState())

    init {
        errorHandling()
    }

    private fun errorHandling() = intent {
        ErrorNotifier.errorFlow.collect { error ->
            postSideEffect(AppSideEffect.ShowSnackBar(error))
        }
    }

    private var backPressedTime: Long = 0
    private val backPressInterval: Long = 2000 // 2초
    fun backPressed(currentRoute: String?) = intent {
       if(currentRoute == ROUTE_MAIN) mainBackHandler()
       else postSideEffect(AppSideEffect.NavigateToMainRoute)
    }

    private fun mainBackHandler() = intent {
        if (System.currentTimeMillis() - backPressedTime < backPressInterval) {
            deviceManager.finishApp()
        } else {
            postSideEffect(AppSideEffect.ShowSnackBar(resourceProvider.getString(R.string.double_click_exit_toast_message)))
            backPressedTime = System.currentTimeMillis()
        }
    }

}

data class AppUiState(
    val snackBarMessage: String? = null
)

sealed class AppSideEffect {
    data object NavigateToMainRoute: AppSideEffect()
    data object NavigateToAuthActivity : AppSideEffect()
    data class ShowSnackBar(val message: String, val drawableRes: Int? = null) : AppSideEffect()
}