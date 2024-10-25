package com.sachosaeng.app.data.remote.util

import android.util.Log


sealed interface ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>

    sealed interface Failure : ApiResult<Nothing> {
        data class HttpError(val code: Int? = -1, val message: String? = "unknown", val body: String? = "unknown") : Failure

        data class NetworkError(val throwable: Throwable) : Failure

        data class UnknownApiError(val throwable: Throwable) : Failure

        fun safeThrowable(): Throwable = when (this) {
            is HttpError -> IllegalStateException("$body")
            is NetworkError -> throwable
            is UnknownApiError -> throwable
        }
    }

    fun isSuccess(): Boolean = this is Success

    fun isFailure(): Boolean = this is Failure

    fun getOrThrow(): T {
        throwOnFailure()
        return (this as? Success<T>)?.data ?: throw IllegalStateException("No success result found.")
    }

    fun getOrNull(): T? =
        when (this) {
            is Success -> data
            else -> null
        }

    fun failureOrThrow(): Failure {
        throwOnSuccess()
        return this as Failure
    }

    fun exceptionOrNull(): Throwable? =
        when (this) {
            is Failure -> safeThrowable()
            else -> null
        }

    companion object {
        fun <R> successOf(result: R): ApiResult<R> = Success(result)
    }
}

internal fun ApiResult<*>.throwOnFailure() {
    if (this is ApiResult.Failure) throw safeThrowable()
}

internal fun ApiResult<*>.throwOnSuccess() {
    if (this is ApiResult.Success) throw IllegalStateException("Cannot be called under Success conditions.")
}

inline fun <T> ApiResult<T>.onFailure(action: (error: ApiResult.Failure) -> Unit): ApiResult<T> {
    if (isFailure()) action(failureOrThrow())
    return this
}

inline fun <T> ApiResult<T>.onSuccess(action: (value: T) -> Unit): ApiResult<T> {
    if (isSuccess()) getOrNull()?.let { action(it) }
    return this
}