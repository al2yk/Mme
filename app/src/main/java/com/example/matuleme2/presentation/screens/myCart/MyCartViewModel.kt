package com.example.matuleme2.presentation.screens.myCart

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleme2.data.states.ProductState
import com.example.matuleme2.domain.Constants
import com.example.matuleme2.domain.repository.UserRepository
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class MyCartViewModel:ViewModel() {
    private val _state = mutableStateOf(ProductState())
    val state: ProductState get() = _state.value

    fun updatestate(newState: ProductState){
        _state.value = newState}


    fun GetCarts(){
        viewModelScope.launch {
            try {


            }
            catch (e:Exception){

            }
        }
    }
}
