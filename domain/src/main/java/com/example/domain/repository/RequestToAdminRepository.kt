package com.example.domain.repository

import Card

interface RequestToAdminRepository {
    suspend fun sendRequestToAdmin(request: String);
}