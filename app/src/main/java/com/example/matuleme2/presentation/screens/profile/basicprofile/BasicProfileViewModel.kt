package com.example.matuleme2.presentation.screens.profile.basicprofile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleme2.data.models.User
import com.example.matuleme2.data.states.BasicProfileState
import com.example.matuleme2.domain.Constants
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.launch

class BasicProfileViewModel:ViewModel() {

    private val _state = mutableStateOf(BasicProfileState())
    val state: BasicProfileState get() = _state.value

    fun updatestate(newstate:BasicProfileState){_state.value=newstate}

    fun GetNameAndSurname(user: User){
        viewModelScope.launch {
            try {
                val currentUser = Constants.supabase.auth.currentUserOrNull()
                Log.d("name", user.name)
                Log.d("surname", user.surname)
                if(currentUser != null) {
                    Log.d("curUser", currentUser.toString())

                    if (currentUser.id == user.idUser){


                    }
                }
            }catch (e:Exception){}
        }
    }
}