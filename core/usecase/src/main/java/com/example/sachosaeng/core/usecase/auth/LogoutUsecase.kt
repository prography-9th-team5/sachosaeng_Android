package com.sachosaeng.app.core.usecase.auth

import com.sachosaeng.app.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUsecase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke(): Flow<Boolean> = repository.logout()
}