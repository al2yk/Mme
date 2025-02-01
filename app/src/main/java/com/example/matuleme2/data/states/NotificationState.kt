package com.example.matuleme2.data.states

import com.example.matuleme2.data.models.Notification

data class NotificationState(
    var notificationList: List<Notification> = emptyList()
)