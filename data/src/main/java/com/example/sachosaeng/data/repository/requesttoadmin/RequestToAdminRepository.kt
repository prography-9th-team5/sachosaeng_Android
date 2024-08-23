package com.example.sachosaeng.data.repository.requesttoadmin

interface RequestToAdminRepository {
    suspend fun sendRequestToAdmin(request: String);
}