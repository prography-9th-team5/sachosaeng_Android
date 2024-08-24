package com.example.sachosaeng.core.usecase.setting

import com.example.sachosaeng.core.usecase.NoParameterUseCase
import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.setting.SettingRepository

class UpdateVersionUsecase(private val repository: SettingRepository) : NoParameterUseCase<Unit> {
    override operator fun invoke() = repository.updateVersion()
}