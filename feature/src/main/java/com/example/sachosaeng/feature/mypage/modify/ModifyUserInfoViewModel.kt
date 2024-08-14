package com.example.sachosaeng.feature.mypage.modify

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.feature.signup.selectusertype.UserType
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ModifyUserInfoViewModel : ViewModel(), ContainerHost<ModifiyUserInfoUiState, Unit> {
    override val container: Container<ModifiyUserInfoUiState, Unit> = container(
        ModifiyUserInfoUiState(
            userType = UserType.JOBSEEKER,
            userName = "사초생",
            canSave = false
        )
    )

    fun onNickNameChange(nickName: String) = intent {
        reduce {
            state.copy(userName = nickName)
        }
    }

    fun onUserTypeSelect(userType: UserType) = intent {
        reduce {
            state.copy(userType = userType)
        }
    }

    fun onWithdraw() = intent {
        reduce {
            state.copy()
        }
    }
}