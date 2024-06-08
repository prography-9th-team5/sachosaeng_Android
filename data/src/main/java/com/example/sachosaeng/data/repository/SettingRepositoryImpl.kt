package com.example.sachosaeng.data.repository

import com.example.sachosaeng.domain.repository.SettingRepository

class SettingRepositoryImpl : com.example.sachosaeng.domain.repository.SettingRepository {
    override suspend fun getPrivacyPolicyUrl(): String {
        TODO("Not yet implemented")
    }

    override suspend fun getTermsOfServiceUrl(): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateVersion() {
        TODO("Not yet implemented")
    }

}