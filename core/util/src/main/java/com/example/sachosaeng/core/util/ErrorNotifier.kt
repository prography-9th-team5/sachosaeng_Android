package com.example.sachosaeng.core.util

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.IOException

object ErrorNotifier {
    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow: SharedFlow<String> = _errorFlow

    data class ErrorResponse(val code: Int, val message: String, val details: String?)

    @OptIn(DelicateCoroutinesApi::class)
    fun notifyError(error: Throwable?) {
        GlobalScope.launch {
            error?.message?.let {
                try {
                    _errorFlow.emit(getErrorMessage(error))
                } catch (e: SerializationException) {
                    println("JSON 파싱 실패: ${e.message}")
                }
            }
        }
    }

    private fun getErrorMessage(error: Throwable): String {
        val jsonString = error.message!!
        val errorResponse = Json.decodeFromString<ErrorResponse>(jsonString).message

        return when (error) {
            is IOException -> "네트워크 에러가 발생했습니다. 다시 시도해주세요."
            else -> errorResponse
        }
    }
}