package com.example.matuleme2.data.models.states

import com.example.matuleme2.data.models.Category
import com.example.matuleme2.data.models.Sneaker
import com.example.matuleme2.presentation.screens.main.MainScreenViewState

data class MainScreenSearchState (
    val search: String = "",
    var sneakers: List<Sneaker> = listOf(),
    var listIdFavSneakers: List<String> = listOf(),
    var categories: List<Category> = listOf(),
    var selectedCategory: Category = Category("", "Все"),
    var viewState: MainScreenViewState = MainScreenViewState.Main
)
