package com.example.sachosaeng.core.usecase.auth

import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JoinUseCase @Inject constructor(
    private val repository: AuthRepository
) : Usecase<String, Flow<Unit>> {
    override fun invoke(email: String): Flow<Unit> = repository.join(email = "test1@naver.com")
}