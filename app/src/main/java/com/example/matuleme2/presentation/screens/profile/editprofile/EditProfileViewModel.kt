package com.example.matuleme2.presentation.screens.profile.editprofile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.matuleme2.data.states.EditProfileState

class EditProfileViewModel: ViewModel() {


    private val _state = mutableStateOf(EditProfileState())
    val state:EditProfileState get() = _state.value

    fun updatestate(newstate:EditProfileState){_state.value=newstate}

}