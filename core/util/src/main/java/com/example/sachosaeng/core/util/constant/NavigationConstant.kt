package com.sachosaeng.app.core.util.constant

import com.sachosaeng.app.core.util.BuildConfig

object NavigationConstant {
    object SignUp {
        const val ROUTE_SIGNUP = "signUp"
        const val SELECT_USER_TYPE = "selectUserType"
        const val SIGNUP_DEEP_LINK = "app://${BuildConfig.APP_URL}/$ROUTE_SIGNUP"
    }

    object Main {
        const val ROUTE_MAIN = "main"
        const val MAIN_DEEP_LINK = "app://${BuildConfig.APP_URL}/$ROUTE_MAIN"
    }
}