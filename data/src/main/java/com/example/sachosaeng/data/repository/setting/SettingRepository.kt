package com.sachosaeng.app.data.repository.setting

interface SettingRepository {
    fun getPrivacyPolicyUrl(): String
    fun getTermsOfServiceUrl(): String
    fun updateVersion()
}