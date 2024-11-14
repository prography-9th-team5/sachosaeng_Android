package com.sachosaeng.app.core.usecase.request_to_admin

import com.sachosaeng.app.core.usecase.NoParameterUseCase
import com.sachosaeng.app.core.usecase.Usecase
import com.sachosaeng.app.data.repository.requesttoadmin.RequestToAdminRepository

class SendRequestToAdminUsecase(
    private val repository: RequestToAdminRepository,
    val request: String
) : NoParameterUseCase<Unit> {
    override operator fun invoke() = repository.sendRequestToAdmin(request = request)
}