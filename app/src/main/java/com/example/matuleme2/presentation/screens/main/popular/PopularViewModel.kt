package com.example.matuleme2.presentation.screens.main.popular

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleme2.data.models.Sneaker
import com.example.matuleme2.data.states.PopularState
import com.example.matuleme2.domain.Constants
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class PopularViewModel : ViewModel() {

    private val _state = mutableStateOf(PopularState())
    val state: PopularState get() = _state.value

    fun updatestate(newstate: PopularState) {
        _state.value = newstate
    }

    fun getData() {
        viewModelScope.launch {
            try {
                val ListPopular = Constants.supabase.from("sneakers").select().decodeList<Sneaker>()
                    .toMutableList()

                updatestate(_state.value.copy(Sneakers = ListPopular))

            } catch (e: Exception) {
            }
        }
    }
}