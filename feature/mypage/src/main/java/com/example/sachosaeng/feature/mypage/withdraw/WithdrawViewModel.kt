package com.sachosaeng.app.feature.mypage.withdraw

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sachosaeng.app.core.ui.R.string
import com.example.sachosaeng.core.util.ResourceProvider
import com.sachosaeng.app.core.ui.WithdrawReason
import com.sachosaeng.app.core.usecase.auth.WithdrawUsecase
import com.sachosaeng.app.core.util.manager.DeviceManager
import com.sachosaeng.app.feature.mypage.navigation.USER_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class WithdrawViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val resourceProvider: ResourceProvider,
    private val withdrawUsecase: WithdrawUsecase,
    private val deviceManager: DeviceManager,
) : ViewModel(), ContainerHost<WIthdrawUiState, WithdrawSideEffect> {
    override val container: Container<WIthdrawUiState, WithdrawSideEffect> =
        container(WIthdrawUiState())
    private val userName = savedStateHandle.get<String>(USER_NAME)

    fun getUserName() = intent {
        reduce {
            state.copy(
                userName = userName ?: ""
            )
        }
    }

    fun changeSelectedReason(selectedReason: WithdrawReason) = intent {
        reduce {
            state.copy(
                selectedReason = selectedReason,
                reasonForWithdrawDetailFieldIsOpened = selectedReason == WithdrawReason.ETC
            )
        }
    }

    fun changeReasonForWithdrawDetail(reasonDetail: String) = intent {
        reduce {
            state.copy(reasonForWithdrawDetail = reasonDetail)
        }
    }

    fun withdraw() = intent {
        when(state.selectedReason) {
            null -> postSideEffect(WithdrawSideEffect.ShowSnackbar(resourceProvider.getString(string.withdraw_reason_required)))
            else -> {
                val reason = resourceProvider.getString(state.selectedReason!!.userTypeLabelRes)
                withdrawUsecase(reason).collectLatest {
                    postSideEffect(WithdrawSideEffect.ShowSnackbar(resourceProvider.getString(string.withdraw_complete_toast_message)))
                    delay(2000)
                    deviceManager.finishApp()
                }
            }
        }
    }
}

sealed class WithdrawSideEffect {
    data class ShowSnackbar(val message: String) : WithdrawSideEffect()
}