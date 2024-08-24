package com.example.sachosaeng.data.repository.setting

interface SettingRepository {
    fun getPrivacyPolicyUrl(): String
    fun getTermsOfServiceUrl(): String
    fun updateVersion()
}