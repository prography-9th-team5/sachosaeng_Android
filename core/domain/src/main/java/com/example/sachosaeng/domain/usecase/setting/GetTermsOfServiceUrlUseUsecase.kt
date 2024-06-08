package com.example.sachosaeng.domain.usecase.setting

import com.example.sachosaeng.domain.repository.SettingRepository
import com.example.sachosaeng.domain.usecase.Usecase

class GetTermsOfServiceUrlUseUsecase(private val repository: com.example.sachosaeng.domain.repository.SettingRepository) :
    com.example.sachosaeng.domain.usecase.Usecase<String> {
    override suspend fun invoke() = repository.getTermsOfServiceUrl()
}