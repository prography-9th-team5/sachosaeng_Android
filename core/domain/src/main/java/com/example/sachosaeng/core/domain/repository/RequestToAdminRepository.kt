package com.example.sachosaeng.core.domain.repository

interface RequestToAdminRepository {
    suspend fun sendRequestToAdmin(request: String);
}