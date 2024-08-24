package com.example.sachosaeng.feature.signup.selectusertype

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.usecase.user.SetUserTypeUseCase
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
class SelectUserTypeViewModel @Inject constructor(
    private val setUserTypeUseCase: SetUserTypeUseCase,
) : ViewModel(), ContainerHost<SelectUserTypeUiState, SelectUserTypeSideEffect> {
    override val container: Container<SelectUserTypeUiState, SelectUserTypeSideEffect> =
        container(SelectUserTypeUiState())

    fun changeSelectUserType(userType: UserType) = intent {
        reduce {
            state.copy(selectedType = userType)
        }
    }

    fun saveSelectedUserTypeAndMoveToNext() = intent {
        setUserTypeUseCase(state.selectedType.name).collectLatest {
            postSideEffect(SelectUserTypeSideEffect.MoveToSelectCategoryScreen)
        }
    }
}

sealed class SelectUserTypeSideEffect {
    data object MoveToSelectCategoryScreen : SelectUserTypeSideEffect()
}