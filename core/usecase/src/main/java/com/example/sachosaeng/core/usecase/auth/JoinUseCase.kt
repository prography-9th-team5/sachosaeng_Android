package com.sachosaeng.app.core.usecase.auth

import com.sachosaeng.app.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JoinUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(email: String, userType: String): Flow<Boolean> =
        repository.join(email = email, userType = userType)
}