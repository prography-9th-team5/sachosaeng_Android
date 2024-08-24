package com.example.sachosaeng.data.datasource.datastore

interface AuthDataStore {
    suspend fun setEmail(email: String): Boolean
    suspend fun getEmail(): String
}