package com.example.sachosaeng.domain.repository

import Card

interface RequestToAdminRepository {
    suspend fun sendRequestToAdmin(request: String);
}