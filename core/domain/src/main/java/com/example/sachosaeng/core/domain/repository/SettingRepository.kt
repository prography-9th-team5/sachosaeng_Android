package com.example.sachosaeng.core.domain.repository

interface SettingRepository {
    suspend fun getPrivacyPolicyUrl(): String
    suspend fun getTermsOfServiceUrl(): String
    suspend fun updateVersion()
}