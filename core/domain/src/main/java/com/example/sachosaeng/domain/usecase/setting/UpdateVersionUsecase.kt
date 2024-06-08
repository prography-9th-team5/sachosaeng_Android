package com.example.sachosaeng.domain.usecase.setting

import com.example.sachosaeng.domain.repository.SettingRepository
import com.example.sachosaeng.domain.usecase.Usecase

class UpdateVersionUsecase(private val repository: com.example.sachosaeng.domain.repository.SettingRepository) :
    com.example.sachosaeng.domain.usecase.Usecase<Unit> {
    override suspend fun invoke() = repository.updateVersion()
}