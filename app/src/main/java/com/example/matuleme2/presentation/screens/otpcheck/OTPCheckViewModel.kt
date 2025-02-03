package com.example.matuleme2.presentation.screens.otpcheck

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme2.domain.Constants
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.example.matuleme2.presentation.ui.theme.red
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.launch

class OTPCheckViewModel(private val controller: NavHostController) : ViewModel() {

    //Отправка ОТП кода
    fun checkOtpCode(email: String, code: String) {
        var verificationSuccess = false
        viewModelScope.launch {
            try {
                Log.d("код", code)
                //проверка токена
                Constants.supabase.auth.verifyEmailOtp(
                    type = OtpType.Email.EMAIL, email = email,
                    token = code
                )
                verificationSuccess = true

                Log.d("проверка кода", "Всё хорошо")
            } catch (e: Exception) {
                verificationSuccess = false
                Log.d("проверка кода | ошибка", e.message.toString())
            }
        }
        if(verificationSuccess){
                //ДОДЕЛАТЬ: добавить экран смены пароля, повторив стиль с прошлых
                // экранов (в макете его нет)
            controller.navigate(NavigationRoutes.SIGNIN) {
                popUpTo(NavigationRoutes.OTP) {
                    inclusive = true
                }
            }
        }
        else{
            var colorInputView: Color = red

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

