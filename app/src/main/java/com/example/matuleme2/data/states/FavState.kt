package com.example.matuleme2.data.states

import com.example.matuleme2.data.models.Sneaker

data class FavState (
    var sneakers: List<Sneaker> = listOf(),
    var fav:Boolean = false
)