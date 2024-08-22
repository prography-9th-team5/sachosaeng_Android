package com.example.sachosaeng.core.usecase.request_to_admin

import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.requesttoadmin.RequestToAdminRepository

class SendRequestToAdminUsecase(
    private val repository: RequestToAdminRepository,
    val request: String
) : Usecase<Unit> {
    override suspend fun invoke() = repository.sendRequestToAdmin(request = request)
}