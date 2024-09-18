package com.example.sachosaeng.data.repository.user

import com.example.sachosaeng.core.model.User
import com.example.sachosaeng.data.model.user.UserInfoResponse

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