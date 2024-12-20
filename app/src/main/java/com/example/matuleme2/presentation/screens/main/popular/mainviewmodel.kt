package com.example.matuleme2.presentation.screens.main.popular

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.matuleme2.data.models.states.MainScreenSearchState

class mainviewmodel :ViewModel() {

    private val _state = mutableStateOf(MainScreenSearchState())
    val state: MainScreenSearchState get() = _state.value

    fun updatestate(newstate: MainScreenSearchState)
    {_state.value = newstate}
}