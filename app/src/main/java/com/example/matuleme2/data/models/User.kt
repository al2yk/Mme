package com.example.matuleme2.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id_user")
    var idUser: String,
    var created_at: String,
    var name: String,
    var surname: String,
    var address: String,
    var email: String,
    var image: String,
)

