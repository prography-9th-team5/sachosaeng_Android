package com.example.sachosaeng.core.domain.usecase.request_to_admin

import com.example.sachosaeng.core.domain.repository.RequestToAdminRepository
import com.example.sachosaeng.core.domain.usecase.Usecase

class SendRequestToAdminUsecase(
    private val repository: RequestToAdminRepository,
    val request: String
) : Usecase<Unit> {
    override suspend fun invoke() = repository.sendRequestToAdmin(request = request)
}