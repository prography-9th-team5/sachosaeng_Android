package com.sachosaeng.app.core.util.extension

object IntExtension {
    fun Int.toNumberOfPeople(): String {
        return "${this}명 참여중"
    }
}