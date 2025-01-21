package com.example.matuleme2.data.states

import com.example.matuleme2.data.models.Sneaker

data class PopularState (
    var Sneakers: List<Sneaker> = listOf(),
    var Favourite: Boolean = false
)