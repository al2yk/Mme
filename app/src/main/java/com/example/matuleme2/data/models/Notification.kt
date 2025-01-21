package com.example.matuleme2.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Notification(
    var id_notification: String,
    var title: String,
    var discription: String,
    var date: String,
    var id_user: String
)