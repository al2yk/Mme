package com.example.matuleme2.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Sneaker(
    var id_sneaker: String,
    var title: String,
    var small_description: String,
    var full_description: String,
    var image: String,
    var cost: Float,

    //Нужно убрать
//    var is_favourite: Boolean,

    var id_category: String,
    var is_popular: Boolean,
    var small_title: String,
)

