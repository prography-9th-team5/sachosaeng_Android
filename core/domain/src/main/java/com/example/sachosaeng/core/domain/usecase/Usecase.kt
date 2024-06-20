package com.example.sachosaeng.core.domain.usecase

import java.util.Optional

interface Usecase<RESULT> {
    operator suspend fun invoke(): RESULT
}
