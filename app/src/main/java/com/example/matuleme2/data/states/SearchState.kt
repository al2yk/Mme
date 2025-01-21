package com.example.matuleme2.data.states

import com.example.matuleme2.data.models.Sneaker

data class SearchState(
    var searchtext: String = "",
    var sneakerslist: List<Sneaker> = emptyList()
)