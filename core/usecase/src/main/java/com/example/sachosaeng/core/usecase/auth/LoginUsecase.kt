package com.example.sachosaeng.core.usecase.auth

import com.example.sachosaeng.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUsecase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke(id: String): Flow<Boolean> = repository.login(email = id)
}