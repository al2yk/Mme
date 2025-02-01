package com.example.matuleme2.presentation.screens.notification

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleme2.data.models.Notification
import com.example.matuleme2.data.states.NotificationState
import com.example.matuleme2.data.states.SearchState
import com.example.matuleme2.domain.Constants
import com.example.matuleme2.domain.repository.UserRepository
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class NotificationViewModel:ViewModel() {

    private val _state = mutableStateOf(NotificationState())
    val state: NotificationState get() = _state.value

    fun updatestate(newState: NotificationState){_state.value =newState}

    fun getNotification(){
        viewModelScope.launch {
            try {
                val noti = Constants.supabase.from("Notification").select()
                {
                    filter {
                        ilike(
                            "id",
                            "%${UserRepository.uuidCurrentUser}"
                        )
                    }
                }.decodeList<Notification>()

                updatestate(_state.value.copy(notificationList = noti))
                Log.d("notification", noti.size.toString())

            }
            catch (e:Exception){

            }
        }
    }
}