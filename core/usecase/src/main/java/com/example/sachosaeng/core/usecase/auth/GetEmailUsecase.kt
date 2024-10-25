package com.sachosaeng.app.core.usecase.auth

import com.sachosaeng.app.data.repository.auth.AuthRepository
import javax.inject.Inject

class GetEmailUsecase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): String = repository.getEmail()
}