package com.example.sachosaeng.domain.usecase.request_to_admin

import com.example.sachosaeng.domain.repository.RequestToAdminRepository
import com.example.sachosaeng.domain.usecase.Usecase

class SendRequestToAdminUsecase(
    private val repository: com.example.sachosaeng.domain.repository.RequestToAdminRepository,
    val request: String
) : com.example.sachosaeng.domain.usecase.Usecase<Unit> {
    override suspend fun invoke() = repository.sendRequestToAdmin(request = request)
}