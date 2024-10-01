package com.sachosaeng.app.core.usecase.setting

import com.sachosaeng.app.core.usecase.NoParameterUseCase
import com.sachosaeng.app.core.usecase.Usecase
import com.sachosaeng.app.data.repository.setting.SettingRepository

class UpdateVersionUsecase(private val repository: SettingRepository) : NoParameterUseCase<Unit> {
    override operator fun invoke() = repository.updateVersion()
}