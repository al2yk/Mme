package com.example.matuleme2.presentation.screens.profile.editprofile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleme2.data.models.User
import com.example.matuleme2.data.states.EditProfileState
import com.example.matuleme2.domain.Constants
import com.yandex.mapkit.UserData
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class EditProfileViewModel : ViewModel() {


    private val _state = mutableStateOf(EditProfileState())
    val state: EditProfileState get() = _state.value

    fun updatestate(newstate: EditProfileState) {
        _state.value = newstate
    }


    // Загрузка данных из базы
    fun loadProfileData() {
        viewModelScope.launch {
            try {
                val currentUser = Constants.supabase.auth.currentUserOrNull()
                currentUser?.let { user ->
                    val response = Constants.supabase.from("users")
                        .select() {
                            filter {
                                eq("id_user", currentUser.id)
                            }
                        }


                    val userData = response.decodeSingle<User>()

                    _state.value = EditProfileState(
                        name = userData.name,
                        surname = userData.surname,
                        address = userData.address,
                        telephone = userData.phone_number,
                        image = userData.image,
                        email = userData.email,
                        iduser = userData.idUser
                    )
                }
            } catch (e: Exception) {
                Log.e("LOAD_PROFILE", "Error loading data", e)
            }
        }
    }

    // Обновление данных в базе
    fun updateProfile() {
        viewModelScope.launch {
            try {
                val currentUser = Constants.supabase.auth.currentUserOrNull()
                currentUser?.let { user ->
                    Constants.supabase.from("users").update(
                        {
                            set("name", state.name)
                            set("surname", state.surname)
                            set("address", state.address)
                            set("phone_number", state.telephone)
                        }
                    ) {
                        filter { eq("id_user", user.id) }
                    }
                    // Обновляем локальные данные после успешного сохранения
                    loadProfileData()
                }
            } catch (e: Exception) {
                Log.e("UPDATE_PROFILE", "Error updating data", e)
            }
        }
    }

    fun EditProfileData(TableColumn: String) {
        viewModelScope.launch {
            try {
                val currentUser = Constants.supabase.auth.currentUserOrNull()
                Log.d("текущий пользователь", currentUser.toString())
                if (currentUser != null) {
                    Log.d("Проверка", state.name)
                    Constants.supabase.from("users").update(
                        {
                            when (TableColumn) {
                                state.name -> {
                                    set("name", state.name)
                                }

                                state.surname -> {
                                    set("surname", state.surname)
                                }

                                state.address -> {
                                    set("address", state.address)
                                }

                                state.telephone -> {
                                    set("phone_number", state.telephone)
                                }
                            }
                        }

                    ) {
                        filter {
                            eq("id_user", currentUser.id)
                        }
                    }
                    Log.d("Проверка", state.name)
                    updatestate(
                        _state.value.copy(
                            name = state.name,
                            surname = state.surname,
                            address = state.address,
                            telephone = state.telephone
                        )
                    )
                    Log.d("Проверка", state.name)
                    Log.d("пользователь обновился", "супер")

                }
            } catch (e: Exception) {
                Log.d("пользователь не обновился", "плохо")
            }
        }
    }
}