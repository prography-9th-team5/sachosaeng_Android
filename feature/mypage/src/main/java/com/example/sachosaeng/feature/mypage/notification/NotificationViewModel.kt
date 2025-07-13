package com.example.sachosaeng.feature.mypage.notification

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.util.ResourceProvider
import com.sachosaeng.app.feature.mypage.navigation.NOTIFICATION_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject
import com.sachosaeng.app.core.ui.R.string

@HiltViewModel
class NotificationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val resourceProvider: ResourceProvider,
) : ViewModel(), ContainerHost<NotificationUiState, Unit> {
    override val container: Container<NotificationUiState, Unit> = container(NotificationUiState())
    private val notificationType = savedStateHandle.get<Int>(NOTIFICATION_TYPE)

    init {
        println("NotificationViewModel initialized with notificationType: ${notificationType}")
        setNotification(notificationType)
    }

    private fun setNotification(
        notificationType: Int?,
    ) = intent {
        when (notificationType) {
            NotificationType.LEVEL_UP.ordinal -> {
                reduce {
                    state.copy(
                        title = resourceProvider.getString(string.mypage_levelup_notification_title),
                        contents = resourceProvider.getString(string.mypage_levelup_notification_content),
                        isLoading = false,
                        errorMessage = null
                    )
                }
            }

            else -> {}
        }
    }
}

data class NotificationUiState(
    val title: String = "",
    val contents: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

enum class NotificationType {
    LEVEL_UP;

    companion object {
        fun fromOrdinal(ordinal: Int): NotificationType? {
            return entries.getOrNull(ordinal)
        }
    }
}