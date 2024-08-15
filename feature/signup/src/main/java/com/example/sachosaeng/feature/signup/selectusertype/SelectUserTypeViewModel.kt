package com.example.sachosaeng.feature.signup.selectusertype

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.ui.UserType
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SelectUserTypeViewModel @Inject constructor(

) : ViewModel(), ContainerHost<SelectUserTypeUiState, Unit> {
    override val container: Container<SelectUserTypeUiState, Unit> =
        container(SelectUserTypeUiState())

    fun changeSelectUserType(userType: UserType) = intent {
        reduce {
            state.copy(selectedType = userType)
        }
    }

}