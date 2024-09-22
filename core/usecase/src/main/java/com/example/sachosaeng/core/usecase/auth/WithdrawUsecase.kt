package com.example.sachosaeng.core.usecase.auth

import com.example.sachosaeng.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WithdrawUsecase @Inject constructor(private val repositroy: UserRepository) {
    operator fun invoke(reason: String): Flow<Unit> = repositroy.withdraw(reason)
}