package com.example.sachosaeng.data.remote.oauth

class OAuthRepository {
    suspend fun refresh(): String {
        return "newToken"
    }
}