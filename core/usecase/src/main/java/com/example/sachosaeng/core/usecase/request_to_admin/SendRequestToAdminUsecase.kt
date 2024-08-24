package com.example.sachosaeng.core.usecase.request_to_admin

import com.example.sachosaeng.core.usecase.NoParameterUseCase
import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.requesttoadmin.RequestToAdminRepository

class SendRequestToAdminUsecase(
    private val repository: RequestToAdminRepository,
    val request: String
) : NoParameterUseCase<Unit> {
    override operator fun invoke() = repository.sendRequestToAdmin(request = request)
}