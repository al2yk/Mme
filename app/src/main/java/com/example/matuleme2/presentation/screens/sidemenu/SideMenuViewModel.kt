package com.example.matuleme2.presentation.screens.sidemenu

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.matuleme2.data.states.SideMenuState

class SideMenuViewModel :ViewModel() {

    private val _state = mutableStateOf(SideMenuState())
    val state: SideMenuState get() = _state.value

    fun updatestate(newstate:SideMenuState){_state.value = newstate}



}