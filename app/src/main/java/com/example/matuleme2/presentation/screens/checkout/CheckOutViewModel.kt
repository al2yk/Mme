package com.example.matuleme2.presentation.screens.checkout

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.matuleme2.data.states.CheckOutState
import com.example.matuleme2.data.states.ForgatePasswordState

class CheckOutViewModel:ViewModel() {

    private val _state = mutableStateOf(CheckOutState())
    val state: CheckOutState get() = _state.value


    fun updatestate(newstate: CheckOutState) {
        _state.value = newstate
    }
}