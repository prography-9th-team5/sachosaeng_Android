package com.example.sachosaeng.core.usecase.setting

import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.setting.SettingRepository

class GetTermsOfServiceUrlUseUsecase(private val repository: SettingRepository) : Usecase<String> {
    override suspend fun invoke() = repository.getTermsOfServiceUrl()
}