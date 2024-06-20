package com.example.sachosaeng.core.domain.usecase.setting

import com.example.sachosaeng.core.domain.repository.SettingRepository
import com.example.sachosaeng.core.domain.usecase.Usecase

class UpdateVersionUsecase(private val repository: SettingRepository) : Usecase<Unit> {
    override suspend fun invoke() = repository.updateVersion()
}