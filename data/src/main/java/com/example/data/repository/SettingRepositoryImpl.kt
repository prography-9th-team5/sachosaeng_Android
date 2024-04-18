package com.example.data.repository

import com.example.domain.repository.SettingRepository

class SettingRepositoryImpl : SettingRepository {
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