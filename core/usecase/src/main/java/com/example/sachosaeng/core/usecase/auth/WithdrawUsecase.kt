package com.example.sachosaeng.core.usecase.auth

import com.example.sachosaeng.core.usecase.NoParameterUseCase
import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.user.UserRepository

class WithdrawUsecase(private val repositroy : UserRepository) : NoParameterUseCase<Unit> {
    override operator fun invoke() = repositroy.withdraw()
}