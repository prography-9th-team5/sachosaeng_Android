package com.sachosaeng.app.data.repository.requesttoadmin

interface RequestToAdminRepository {
    fun sendRequestToAdmin(request: String);
}