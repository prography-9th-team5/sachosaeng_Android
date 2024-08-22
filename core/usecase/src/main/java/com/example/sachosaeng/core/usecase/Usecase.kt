package com.example.sachosaeng.core.usecase

interface Usecase<RESULT> {
    operator suspend fun invoke(): RESULT
}
