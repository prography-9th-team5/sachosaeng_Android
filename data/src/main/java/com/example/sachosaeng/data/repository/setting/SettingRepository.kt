package com.example.sachosaeng.data.repository.setting

interface SettingRepository {
    suspend fun getPrivacyPolicyUrl(): String
    suspend fun getTermsOfServiceUrl(): String
    suspend fun updateVersion()
}