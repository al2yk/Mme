package com.example.matuleme2.presentation.screens.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme2.data.models.Category
import com.example.matuleme2.data.models.Sneaker
import com.example.matuleme2.data.models.User
import com.example.matuleme2.data.models.states.MainScreenSearchState
import com.example.matuleme2.domain.Constants
import com.example.matuleme2.domain.Requests
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class mainviewmodel() :ViewModel() {

    private val _state = mutableStateOf(MainScreenSearchState())
    val state: MainScreenSearchState get() = _state.value

    fun updatestate(newstate: MainScreenSearchState)
    {_state.value = newstate}

    fun getData() {
        viewModelScope.launch {
            try {

                //Взяли категории из базы
                val listCategory = Constants.supabase.from("categories")
                    .select().decodeList<Category>().toMutableList()

                //Первый нулевой элемент
                listCategory.add(0, Category("", "Все"))

                //Обновляем состояние на лист категорий
                updatestate(_state.value.copy(categories = listCategory))

                val listSneakers = Requests.getAllSneakers()
                val listIdFavSneakers = Requests.getIdFavSneakers()

                updatestate(_state.value.copy(sneakers = listSneakers,
                    listIdFavSneakers = listIdFavSneakers))
                Log.d("SearchSne", listSneakers.size.toString())
                Log.d("кроссовки", listSneakers.toString())
                Log.d("категории", listCategory.toString())
            } catch (e: Exception) {
                Log.d("кроссовки", e.message.toString())
            }
        }
    }

    fun clickFavIcon(sneaker: Sneaker) {
        if(state.listIdFavSneakers.contains(sneaker.id_sneaker)) deleteFav(sneaker.id_sneaker)
        else addFav(sneaker.id_sneaker)
    }

    fun deleteFav(sneakerId: String) {
        viewModelScope.launch {
            try {
                Requests.deleteFavItem(sneakerId)
                Log.d("удаление из избранного", "ок")
                getData()
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
    }
}