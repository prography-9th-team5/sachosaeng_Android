package com.example.sachosaeng.data.datasource.datastore

interface UserDataStore {
    suspend fun setUserType(type: String): Boolean
    suspend fun getUserType(): String
}