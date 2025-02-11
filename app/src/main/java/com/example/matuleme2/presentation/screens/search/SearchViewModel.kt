package com.example.matuleme2.presentation.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleme2.data.models.Sneaker
import com.example.matuleme2.data.states.SearchState
import com.example.matuleme2.domain.Constants
import com.example.matuleme2.domain.Requests
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SearchViewModel : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: SearchState get() = _state.value

    private val _sneakers = MutableStateFlow<List<Sneaker>>(emptyList())
    val sneakersvm: StateFlow<List<Sneaker>> = _sneakers

    fun updateState(newState: SearchState) {
        _state.value = newState
    }

    /*val text = mutableStateOf("")*/

    fun searchSneaker(sneakerName: String) {
        viewModelScope.launch {
            try {
                val listsneaker = Constants.supabase.from("sneakers").select()
                {
                    filter {
                        ilike(
                            "small_title",
                            "%${sneakerName}%"
                        )
                    }
                }.decodeList<Sneaker>()

                val listIdFavSneakers = Requests.getIdFavSneakers()

                updateState(
                    _state.value.copy(
                        listIdFavSneakers = listIdFavSneakers
                    )
                )

                updateState(_state.value.copy(sneakerslist = listsneaker))
                Log.d("Search", listsneaker.size.toString())

            } catch (e: Exception) {
                Log.e("SearchViewModel", "Error", e)
            }
        }
    }

/*    fun clickFavIcon(sneaker: Sneaker) {
        if (state.listIdFavSneakers.contains(sneaker.id_sneaker)) deleteFav(sneaker.id_sneaker)
        else addFav(sneaker.id_sneaker)
    }

    fun deleteFav(sneakerId: String) {
        viewModelScope.launch {
            try {
                Requests.deleteFavItem(sneakerId)
                Log.d("удаление из избранного", "ок")
                searchSneaker()
            } catch (e: Exception) {
                Log.d("удаление из избранного | ошибка", e.message.toString())
            }
        }
    }

    fun addFav(sneakerId: String) {
        viewModelScope.launch {
            try {
                Requests.addSneakerInFav(sneakerId)
                Log.d("добавление в избранное", "ок")
                getData()
            } catch (e: Exception) {
                Log.d("добавление в избранное | ошибка", e.message.toString())
            }
        }
    }*/
}