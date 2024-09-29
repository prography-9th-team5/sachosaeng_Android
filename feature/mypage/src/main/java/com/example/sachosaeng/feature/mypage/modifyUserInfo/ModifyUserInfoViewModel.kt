package com.example.sachosaeng.feature.mypage.modifyUserInfo

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.ui.ResourceProvider
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.usecase.user.GetMyInfoUsecase
import com.example.sachosaeng.core.usecase.user.SetUserNickNameUseCase
import com.example.sachosaeng.core.ui.R.string
import com.example.sachosaeng.core.usecase.user.SetUserTypetoRemoteUseCase
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
class ModifyUserInfoViewModel @Inject constructor(
    private val getMyInfoUsecase: GetMyInfoUsecase,
    private val setUserNickNameUseCase: SetUserNickNameUseCase,
    private val setUserTypetoRemoteUseCase: SetUserTypetoRemoteUseCase,
    private val resourceProvider: ResourceProvider
) : ViewModel(),
    ContainerHost<ModifiyUserInfoUiState, ModifyUserInfoSideEffect> {
    override val container: Container<ModifiyUserInfoUiState, ModifyUserInfoSideEffect> = container(
        ModifiyUserInfoUiState(
            userType = UserType.JOB_SEEKER,
            userName = "",
            canSave = false
        )
    )

    init {
        getMyInfo()
    }

    private fun getMyInfo() = intent {
        getMyInfoUsecase().collectLatest { userInfo ->
            reduce {
                state.copy(
                    userName = userInfo.name,
                    userType = UserType.getType(userInfo.userType) ?: UserType.OTHER
                )
            }
        }
    }

    fun onNickNameChange(nickName: String) = intent {
        reduce {
            state.copy(userName = nickName)
        }
        checkIsSaveButtonEnable()
    }

    fun onUserTypeSelect(userType: UserType) = intent {
        reduce {
            state.copy(userType = userType)
        }
        checkIsSaveButtonEnable()
    }

    fun saveUserInfo() = intent {
        getMyInfoUsecase().collectLatest { userInfo ->
            if (userInfo.name != state.userName) {
                setUserNickNameUseCase(state.userName)
            }
            if (UserType.getType(userInfo.userType) != state.userType) {
                setUserTypetoRemoteUseCase(state.userType.name)
            }
        }.also {
            postSideEffect(ModifyUserInfoSideEffect.ShowSnackBar(resourceProvider.getString(string.saved_snackbar)))
        }
    }

    private fun checkIsSaveButtonEnable() = intent {
        reduce {
            state.copy(canSave = state.userName.isNotEmpty())
        }
    }
}

sealed class ModifyUserInfoSideEffect {
    data class ShowSnackBar(val message: String) : ModifyUserInfoSideEffect()
}
