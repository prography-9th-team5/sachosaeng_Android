package com.sachosaeng.app.core.usecase.setting

import com.sachosaeng.app.core.usecase.NoParameterUseCase
import com.sachosaeng.app.core.usecase.Usecase
import com.sachosaeng.app.data.repository.setting.SettingRepository

class GetPrivacyPolicyUrlUsecase(
    private val repository:
    SettingRepository
) : NoParameterUseCase<String> {
    override fun invoke() = repository.getPrivacyPolicyUrl()
}