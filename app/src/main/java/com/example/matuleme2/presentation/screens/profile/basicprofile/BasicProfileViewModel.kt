package com.example.matuleme2.presentation.screens.profile.basicprofile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.matuleme2.data.states.BasicProfileState

class BasicProfileViewModel:ViewModel() {

    private val _state = mutableStateOf(BasicProfileState())
    val state: BasicProfileState get() = _state.value

    fun updatestate(newstate:BasicProfileState){_state.value=newstate}

}