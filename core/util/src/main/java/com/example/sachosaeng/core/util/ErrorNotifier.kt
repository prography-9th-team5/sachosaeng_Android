package com.example.sachosaeng.core.util

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.io.IOException

object ErrorNotifier {
    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow: SharedFlow<String> = _errorFlow

    @OptIn(DelicateCoroutinesApi::class)
    fun notifyError(error: Throwable?) {
        GlobalScope.launch {
            if (error != null) {
                _errorFlow.emit(getErrorMessage(error))
            }
        }
    }

    private fun getErrorMessage(error: Throwable): String {
        return when(error) {
            is IOException -> "네트워크 에러가 발생했습니다. 다시 시도해주세요."
            else -> error.message ?: "Unknown error"
        }
    }
}