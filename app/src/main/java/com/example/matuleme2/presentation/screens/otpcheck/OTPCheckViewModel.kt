package com.example.matuleme2.presentation.screens.otpcheck

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme2.data.states.ForgatePasswordState
import com.example.matuleme2.domain.Constants
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.example.matuleme2.presentation.validation.isEmailValid
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth

import kotlinx.coroutines.launch

class OTPCheckViewModel(private val controller: NavHostController) : ViewModel() {

    fun checkOtpCode(email: String, code: String) {
        viewModelScope.launch {
            try {
                Log.d("код", code)
                //проверка токена
                Constants.supabase.auth.verifyEmailOtp(
                    type = OtpType.Email.EMAIL, email = email,
                    token = code
                )
                //ДОДЕЛАТЬ: добавить экран смены пароля, повторив стиль с прошлых
                //экранов (в макете его нет)
                controller.navigate(NavigationRoutes.SIGNIN) {
                    popUpTo(NavigationRoutes.OTP) {
                        inclusive = false
                    }
                }
                Log.d("проверка кода", "Всё хорошо")
            } catch (e: Exception) {
                Log.d("проверка кода | ошибка", e.message.toString())
            }
        }
    }

    //отправка otp если забыл пароль
    fun sendOtpAgain(email: String) {
        viewModelScope.launch {
            try {
                Constants.supabase.auth.resetPasswordForEmail(
                    email = email, redirectUrl = ""
                )
                Log.d("отправка кода ещё раз", "ок")
            } catch (e: Exception) {
                Log.d("отправка кода ещё раз", "не ок: ${e.message}")
            }
        }
    }

}

