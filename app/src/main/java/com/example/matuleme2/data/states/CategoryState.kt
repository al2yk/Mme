package com.example.matuleme2.data.states

import com.example.matuleme2.data.models.Category
import com.example.matuleme2.data.models.Sneaker

data class CategoryState (
    var categories: List<Category> = listOf(),
    var sneakers: List<Sneaker> = listOf(),
)