package com.sachosaeng.app.core.usecase.auth

import com.sachosaeng.app.core.domain.constant.OAuthType
import com.sachosaeng.app.data.repository.auth.AuthRepository
import javax.inject.Inject

class SetEmailUsecase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, type: OAuthType): Boolean {
        return repository.setEmail(email, type)
    }
}