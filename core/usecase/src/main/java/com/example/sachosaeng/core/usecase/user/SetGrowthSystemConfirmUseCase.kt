package com.sachosaeng.app.core.usecase.user

import com.example.sachosaeng.data.repository.user.UserRepository
import javax.inject.Inject

class SetGrowthSystemConfirmUseCase @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(growthSystemConfirmed: Boolean) = repository.setGrowthSystemConfirmed(growthSystemConfirmed = growthSystemConfirmed)
}