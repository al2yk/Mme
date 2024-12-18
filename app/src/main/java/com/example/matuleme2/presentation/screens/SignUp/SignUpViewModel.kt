package com.example.matuleme2.presentation.screens.SignUp

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme2.data.models.User
import com.example.matuleme2.data.states.SignUpState
import com.example.matuleme2.domain.Constants
import com.example.matuleme2.domain.repository.UserRepository
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.example.matuleme2.presentation.validation.isEmailValid
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import java.sql.Timestamp

class SignUpViewModel : ViewModel() {
    private val _state = mutableStateOf(SignUpState())
    val state: SignUpState get() = _state.value

//    @SuppressLint("StaticFieldLeak")
//    lateinit var context: Context

    fun updatestate(newstate: SignUpState) {
        _state.value = newstate
    }

    fun signUp(controller: NavHostController,context: Context) {
        viewModelScope.launch {
            try {
                if (state.email.isEmailValid()) {
                    Constants.supabase.auth.signUpWith(Email) {
                        email = state.email
                        password = state.password
                    }
                    Log.d("sign up success", "супер sign up")

                    val currentUser = Constants.supabase.auth.currentUserOrNull()

                    if (currentUser != null) {
                        Constants.supabase.from("users").insert(
                            User(
                                idUser = currentUser.id,
                                email = state.email,
                                address = "",
                                name = state.name,
                                surname = "",
                                created_at = Timestamp(System.currentTimeMillis()).toString(),
                                image = ""
                            )
                        )
                        Log.d("create user", "супер")
                        UserRepository.act = 2

                        controller.navigate(NavigationRoutes.MAIN) {
                            popUpTo(NavigationRoutes.SIGNUP) {
                                inclusive = true
                            }
                        }
                    }
                } else {
                    Toast.makeText(context, "Неверный формат почты", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.d("sign up error", e.message.toString())
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}