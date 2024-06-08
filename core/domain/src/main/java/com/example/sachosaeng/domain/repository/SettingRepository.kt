package com.example.sachosaeng.domain.repository

interface SettingRepository {
    suspend fun getPrivacyPolicyUrl(): String
    suspend fun getTermsOfServiceUrl(): String
    suspend fun updateVersion()
}