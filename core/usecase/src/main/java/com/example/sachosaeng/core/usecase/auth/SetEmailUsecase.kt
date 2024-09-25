package com.example.sachosaeng.core.usecase.auth

import com.example.sachosaeng.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetEmailUsecase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String): Boolean = repository.setEmail(email)
}