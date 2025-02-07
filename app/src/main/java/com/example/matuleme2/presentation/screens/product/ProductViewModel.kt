package com.example.matuleme2.presentation.screens.product

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleme2.data.states.ProductState
import com.example.matuleme2.domain.Requests
import kotlinx.coroutines.launch

class ProductViewModel:ViewModel() {

    private val _state = mutableStateOf(ProductState())
    val state: ProductState get() = _state.value

    fun updatestate(newState: ProductState){
        _state.value = newState}

    fun getData(){
        viewModelScope.launch {
            try {
                //список кросовок
                val listSneakers = Requests.getAllSneakers()

                updatestate(state.copy(Sneakers = listSneakers))

                Log.d("GetSneakersProduct",listSneakers.toString())
            }
            catch (e:Exception){
                    Log.d("Не удалось получить список кросовок PRODUCt",e.message.toString())
            }
        }
    }
}

sealed class ScreenProduct(title:String){

}