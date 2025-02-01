package com.example.matuleme2.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Favourite(
    var id: String,
    var id_sneaker: String,
    var id_user: String,
)