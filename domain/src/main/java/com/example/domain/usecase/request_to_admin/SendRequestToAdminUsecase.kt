package com.example.domain.usecase.request_to_admin

import com.example.domain.repository.RequestToAdminRepository
import com.example.domain.usecase.Usecase

class SendRequestToAdminUsecase(
    private val repository: RequestToAdminRepository,
    val request: String
) : Usecase<Unit> {
    override suspend fun invoke() = repository.sendRequestToAdmin(request = request)
}