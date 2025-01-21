package com.example.matuleme2.presentation.screens.main.categories

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.matuleme2.data.states.CategoryState

class CategoryViewModel:ViewModel() {

    private val _state = mutableStateOf(CategoryState())
    val state: CategoryState get() = _state.value

    fun updatestate(newstate:CategoryState){_state.value =newstate}


}