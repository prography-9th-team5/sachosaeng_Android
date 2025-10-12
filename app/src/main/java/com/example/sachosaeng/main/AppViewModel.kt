package com.sachosaeng.app.main

import android.graphics.drawable.Drawable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.unit.dp
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
import org.orbitmvi.orbit.syntax.simple.reduce
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
    private val backPressInterval: Long = 2000
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

    fun showLevelUpTooltip() = intent {
        reduce {
            println("Showing level up tooltip")
            state.copy(
                tooltipState = state.tooltipState.copy(
                    isVisible = true,
                    tooltipMessage = resourceProvider.getString(R.string.levelup_tooltip),
                ),
            )
        }
    }

    fun showSnackBar(message: String, drawableRes: Int? = null) = intent {
        postSideEffect(AppSideEffect.ShowSnackBar(message, drawableRes))
    }
}

data class AppUiState (
    val snackBarMessage: String? = null,
    val tooltipState: TooltipState = TooltipState(),
    val snackBarDrawable: Drawable? = null,
)

data class TooltipState (
    val isVisible: Boolean = false,
    val tooltipMessage: String = "",
)

sealed class AppSideEffect {
    data object NavigateToMainRoute: AppSideEffect()
    data object NavigateToAuthActivity : AppSideEffect()
    data class ShowSnackBar(val message: String, val drawableRes: Int? = null) : AppSideEffect()
}