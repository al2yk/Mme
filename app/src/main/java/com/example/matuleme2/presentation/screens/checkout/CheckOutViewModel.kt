package com.example.matuleme2.presentation.screens.checkout

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleme2.data.models.User
import com.example.matuleme2.data.states.CheckOutState
import com.example.matuleme2.data.states.ForgatePasswordState
import com.example.matuleme2.domain.Constants
import com.example.matuleme2.domain.repository.UserRepository
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class CheckOutViewModel : ViewModel() {

    private val _state = mutableStateOf(CheckOutState())
    val state: CheckOutState get() = _state.value



    fun updatestate(newstate: CheckOutState) {
        _state.value = newstate
    }

    fun getUserData(address:String?) {
        viewModelScope.launch {
            try {

                var userdata = Constants.supabase.from("users").select() {
                    filter { eq("id_user", UserRepository.uuidCurrentUser) }
                }.decodeSingle<User>()

                updatestate(
                    state.copy(
                        email = userdata.email,
                        phonenumber = userdata.phone_number,
                        address = userdata.address
                    )
                )

            } catch (e: Exception) {

            }
        }
    }
}