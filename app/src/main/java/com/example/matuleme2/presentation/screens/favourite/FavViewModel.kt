package com.example.matuleme2.presentation.screens.favourite

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleme2.data.models.Sneaker
import com.example.matuleme2.data.states.FavState
import com.example.matuleme2.domain.Requests
import kotlinx.coroutines.launch

class FavViewModel:ViewModel() {
    private val _state = mutableStateOf(FavState())
    val state: FavState get() = _state.value

    fun updatestate(newstate: FavState){_state.value = newstate}


    //Получить данные с базы(свои фав)
    fun getData() {
        viewModelScope.launch {
            try {
                //получаем все кроссовки
                val listSneakers = Requests.getAllSneakers()
                //Получаем id кроссовок в избранном
                val listIdFavSneakers = Requests.getIdFavSneakers()

                updatestate(state.copy(sneakers = listSneakers, idFavSneakers = listIdFavSneakers))

                Log.d("кроссовки", listSneakers.toString())
                Log.d("id кроссовок в избранном", listIdFavSneakers.toString())
                Log.d("избранное", "всё ок")
            } catch (e: Exception) {
                Log.d("избранное | ошибка", e.message.toString())
            }
        }
    }


    //Действия при нажатии иконки фав
    fun clickFavIcon(sneaker: Sneaker) {

        //Если возращается тру(То есть в фав есть кросовок) - удаляется
        if(state.idFavSneakers.contains(sneaker.id_sneaker)) deleteFav(sneaker.id_sneaker)

        //Иначе добавляется ( то есть отсутсвует)
        else addFav(sneaker.id_sneaker)
    }


    //Удаление из таблицы фав кросовок
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

    //Добавление в базу кросовок при нажатии
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