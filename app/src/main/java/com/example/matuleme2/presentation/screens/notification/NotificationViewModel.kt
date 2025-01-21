package com.example.matuleme2.presentation.screens.notification

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleme2.domain.Constants
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class NotificationViewModel:ViewModel() {

    fun getNotification(){
        viewModelScope.launch {
            try {
                val noti = Constants.supabase.from("Notification").select().decodeList<String>()
            }
            catch (e:Exception){}
        }
    }
}