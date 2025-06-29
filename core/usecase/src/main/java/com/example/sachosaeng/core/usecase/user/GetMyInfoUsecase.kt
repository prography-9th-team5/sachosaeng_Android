package com.sachosaeng.app.core.usecase.user

import com.sachosaeng.app.core.model.User
import com.sachosaeng.app.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetMyInfoUsecase @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(): Flow<User> = combine(
    repository.getMyInfo(),
    repository.getGrowthSystemConfirmed()
    ) { user, confirmed ->
        user.copy(userGrowthSystemConfirmed = confirmed)
    }
}