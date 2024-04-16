package com.example.domain.usecase.setting

import com.example.domain.repository.SettingRepository
import com.example.domain.usecase.Usecase

class GetTermsOfServiceUrlUseUsecase(private val repository: SettingRepository) : Usecase<String> {
    override suspend fun invoke() = repository.getTermsOfServiceUrl()
}