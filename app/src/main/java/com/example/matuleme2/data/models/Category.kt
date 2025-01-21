package com.example.matuleme2.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    var id_category: String,
    var category: String
)