package com.example.sachosaeng.core.util

object PushNotificationConstants {
    
    const val CHANNEL_ID = "daily_vote_channel"
    
    object NotificationId {
        const val MORNING = 1001
        const val AFTERNOON = 1002
        const val EVENING = 1003
        const val WEEKLY = 1004
        const val EVENT = 1005
        const val SYSTEM = 1006
    }
    
    object PushType {
        const val MORNING = "morning"
        const val AFTERNOON = "afternoon"
        const val EVENING = "evening"
        const val WEEKLY = "weekly"
        const val EVENT = "event"
        const val SYSTEM = "system"
        const val TEST = "test"
    }
    
    object Priority {
        const val HIGH = "high"
        const val NORMAL = "normal"
        const val LOW = "low"
    }
    
    object Category {
        const val VOTE = "vote"
    }
    
    object Activity {
        const val MAIN_ACTIVITY = "com.sachosaeng.app.main.MainActivity"
    }
    
    object IntentExtra {
        const val PUSH_TYPE = "push_type"
        const val TITLE = "title"
        const val MESSAGE = "message"
        const val IMAGE_URL = "image_url"
        const val NAVIGATE_TO = "navigate_to"
    }
}
