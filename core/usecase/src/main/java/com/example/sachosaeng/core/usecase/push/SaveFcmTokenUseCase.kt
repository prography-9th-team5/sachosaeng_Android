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