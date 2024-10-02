package com.sachosaeng.app.data.repository.user

import com.sachosaeng.app.core.model.User
import com.sachosaeng.app.data.model.user.UserInfoResponse

object UserMapper {
    fun UserInfoResponse.toDomain(): User {
        return User(
            id = userId,
            name = nickname,
            email = "",
            userType = userType
        )
    }
}