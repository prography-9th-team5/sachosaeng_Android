package com.sachosaeng.app.core.usecase.user

import android.graphics.Bitmap
import com.sachosaeng.app.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DownloadProfileImageUseCase @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(bitmap: Bitmap): Flow<Boolean> = repository.downloadProfileImage(bitmap)
}