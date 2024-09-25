package com.example.sachosaeng.data.datasource.datastore

interface AuthDataStore {
    suspend fun setUserId(userId: Int): Boolean
    suspend fun getUserId(): Int
    suspend fun setEmail(email: String): Boolean
    suspend fun getEmail(): String
    suspend fun setAccessToken(token: String): Boolean
    suspend fun getAccessToken(): String
    suspend fun getKakaoAccessToken(): String
    suspend fun setRefreshToken(token: String): Boolean
    suspend fun getRefreshToken(): String
    suspend fun setKakaoLoginToken(token: String): Boolean
}