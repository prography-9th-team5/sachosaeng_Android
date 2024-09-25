package com.example.sachosaeng.core.util.constant

import com.example.sachosaeng.core.util.BuildConfig

object NavigationConstant {
    object SignUp {
        const val SELECT_USER_TYPE = "selectUserType"
        const val SIGNUP_SELECT_USER_TYPE_DEEP_LINK = "app://${BuildConfig.APP_URL}/$SELECT_USER_TYPE"
    }

    object Main {
        const val ROUTE_MAIN = "main"
        const val MAIN_DEEP_LINK = "app://${BuildConfig.APP_URL}/$ROUTE_MAIN"
    }
}