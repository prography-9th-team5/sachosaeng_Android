package com.sachosaeng.app.core.usecase

interface Usecase<in PARAM, out RESULT> {
    operator fun invoke(param: PARAM): RESULT
}

interface NoParameterUseCase<out RESULT> {
    operator fun invoke(): RESULT
}