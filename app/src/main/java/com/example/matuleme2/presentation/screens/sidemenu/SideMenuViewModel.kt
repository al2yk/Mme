package com.example.matuleme2.presentation.screens.sidemenu

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleme2.data.states.SideMenuState
import com.example.matuleme2.domain.Constants
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class SideMenuViewModel : ViewModel() {

    private val _state = mutableStateOf(SideMenuState())
    val state: SideMenuState get() = _state.value

    fun updatestate(newstate: SideMenuState) {
        _state.value = newstate
    }

    fun getuser() {
        viewModelScope.launch {
            try {
                val currentUser = Constants.supabase.auth.currentUserOrNull()
                if(currentUser != null) {
                    Log.d("curUser", currentUser.toString())

                }

                val userdata = Constants.supabase.from("users").select {}
            } catch (e: Exception) {
            }
        }
    }

}