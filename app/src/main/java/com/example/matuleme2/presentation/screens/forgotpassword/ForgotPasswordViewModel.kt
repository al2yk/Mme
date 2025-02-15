package com.example.matuleme2.presentation.screens.forgotpassword

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme2.data.states.ForgatePasswordState
import com.example.matuleme2.domain.Constants
import com.example.matuleme2.presentation.validation.isEmailValid
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    ViewModel() {

    private val _state = mutableStateOf(ForgatePasswordState())
    val state: ForgatePasswordState get() = _state.value

    fun updatestate(newstate: ForgatePasswordState) {
        _state.value = newstate
    }

    //отправка otp если забыл пароль
    fun ForgotPasswordOTP(controller: NavHostController) {
        viewModelScope.launch {
            try {

                if (state.email.isEmailValid()) {
                    updatestate(_state.value.copy(dialog = true))
                    Log.d("forgot password success", "супер forgot password")

                    //magic link
                    /*Constants.supabase.auth.signInWith(OTP) {
                        email = state.email
                        createUser = false
                    }*/
                        //reset password
                    Constants.supabase.auth.resetPasswordForEmail(
                        email = state.email, redirectUrl = ""
                    )
                } else {
                    Log.d("forgot password", "плохо")
                    Toast.makeText(context, "Неверный формат почты", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {

            }
        }
    }
}

