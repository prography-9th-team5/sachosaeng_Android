package com.example.sachosaeng.core.util

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

object ErrorNotifier {
    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow: SharedFlow<String> = _errorFlow

    @OptIn(DelicateCoroutinesApi::class)
    fun notifyError(error: String?) {
        GlobalScope.launch {
            if (error != null) {
                _errorFlow.emit(error)
            }
        }
    }
}