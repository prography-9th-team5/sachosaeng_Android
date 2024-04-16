package com.example.domain.usecase.setting

import com.example.domain.repository.SettingRepository
import com.example.domain.usecase.Usecase

class UpdateVersionUsecase(private val repository: SettingRepository) : Usecase<Unit> {
    override suspend fun invoke() = repository.updateVersion()
}