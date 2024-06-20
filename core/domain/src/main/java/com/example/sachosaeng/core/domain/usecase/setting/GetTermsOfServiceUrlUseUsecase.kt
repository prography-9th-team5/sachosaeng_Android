package com.example.sachosaeng.core.domain.usecase.setting

import com.example.sachosaeng.core.domain.repository.SettingRepository
import com.example.sachosaeng.core.domain.usecase.Usecase

class GetTermsOfServiceUrlUseUsecase(private val repository: SettingRepository) : Usecase<String> {
    override suspend fun invoke() = repository.getTermsOfServiceUrl()
}