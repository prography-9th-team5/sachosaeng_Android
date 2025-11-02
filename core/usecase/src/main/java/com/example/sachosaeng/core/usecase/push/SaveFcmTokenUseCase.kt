package com.example.sachosaeng.core.usecase.push

import android.util.Log
import com.example.sachosaeng.data.repository.user.UserRepository
import javax.inject.Inject

class SaveFcmTokenUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(token: String) {
        if (token.isBlank()) {
            return
        }
        
        try {
            repository.setFcmToken(token = token)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            throw e
        }
    }

    companion object {
        private const val TAG = "SaveFcmTokenUseCase"
    }
}

class TestPushByUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(title: String, message: String) {
        try {
            repository.testPushByUser(title = title, message = message)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            throw e
        }
    }

    companion object {
        private const val TAG = "TestPushByUserUseCase"
    }
}

class TestPushByTokenUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(title: String, message: String) {
        try {
            repository.testPushByToken(
                title = title,
                message = message
            )
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            throw e
        }
    }

    companion object {
        private const val TAG = "TestPushByTokenUseCase"
    }
}