package com.sachosaeng.app.core.usecase.user

import com.example.sachosaeng.data.repository.user.UserRepository
import com.sachosaeng.app.core.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetMyInfoUsecase @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(): Flow<User> = combine(
        repository.getMyInfo(),
        repository.getGrowthSystemConfirmed(),
        repository.getLevelUpNotification(),
    ) { user, confirmed, levelUpNotification ->
        user.copy(userGrowthSystemConfirmed = confirmed, levelUpNotification = levelUpNotification)
    }
}