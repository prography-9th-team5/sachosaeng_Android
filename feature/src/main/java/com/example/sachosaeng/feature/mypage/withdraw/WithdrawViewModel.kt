package com.example.sachosaeng.feature.mypage.withdraw

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.domain.usecase.user.WithdrawUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class WithdrawViewModel @Inject constructor(
    //private val withdrawUsecase: WithdrawUsecase,
) : ViewModel(), ContainerHost<WIthdrawUiState, WithdrawSideEffect> {
    override val container: Container<WIthdrawUiState, WithdrawSideEffect> =
        container(WIthdrawUiState())

    fun getUserName() = intent {
        //todo: navigatoion으로부터 받기
        reduce {
            state.copy(
                userName = "사초생"
            )
        }
    }

    fun changeSelectedReason(selectedReason: ReasonForWithdraw) = intent {
        reduce {
            state.copy(
                selectedReason = selectedReason,
                reasonForWithdrawDetailFieldIsOpened = selectedReason == ReasonForWithdraw.ETC
            )
        }
    }

    fun changeReasonForWithdrawDetail(reasonDetail: String) = intent {
        reduce {
            state.copy(reasonForWithdrawDetail = reasonDetail)
        }
    }

    fun withdraw() = intent {
        when (state.selectedReason) {
            ReasonForWithdraw.NONE -> postSideEffect(WithdrawSideEffect.ShowSnackbar("사유를 선택해주세요"))
            else -> {
               // withdrawUsecase()
            }
        }

    }
}

sealed class WithdrawSideEffect {
    data class ShowSnackbar(val message: String) : WithdrawSideEffect()
}