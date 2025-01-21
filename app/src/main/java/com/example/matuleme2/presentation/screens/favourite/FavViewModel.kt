package com.example.matuleme2.presentation.screens.favourite

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.matuleme2.data.states.CategoryState
import com.example.matuleme2.data.states.FavState

class FavViewModel:ViewModel() {
    private val _state = mutableStateOf(FavState())
    val state: FavState get() = _state.value

    fun updatestate(newstate: FavState){_state.value = newstate}



}