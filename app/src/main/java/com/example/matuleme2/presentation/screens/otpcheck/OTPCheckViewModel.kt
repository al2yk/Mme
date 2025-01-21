package com.example.matuleme2.presentation.screens.otpcheck

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme2.data.models.states.MainScreenSearchState
import com.example.matuleme2.data.states.ForgatePasswordState
import com.example.matuleme2.domain.Constants
import com.example.matuleme2.presentation.validation.isEmailValid
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.OTP

import kotlinx.coroutines.launch

class OTPCheckViewModel(controller: NavHostController):ViewModel() {

    private val _state = mutableStateOf(ForgatePasswordState())
    val state: ForgatePasswordState get() = _state.value

    fun updatestate(newstate: ForgatePasswordState)
    {_state.value = newstate}

    val textlist = listOf(
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        ),
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        ),
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        ),
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        ),
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        ),
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        ),

        )

    val requesterList = listOf(
        FocusRequester(),
        FocusRequester(),
        FocusRequester(),
        FocusRequester(),
        FocusRequester(),
        FocusRequester(),

    )

    fun ForgotPasswordOTP(controller: NavHostController) {
        viewModelScope.launch {
            try {
                Constants.supabase.auth.verifyEmailOtp(type = OtpType.Email.EMAIL, email = state.email,
                    token = textlist.toString())
                Log.d("проверка кода", "Всё хорошо")
            } catch (e: Exception) {
                Log.d("проверка кода | ошибка", e.message.toString())
            }
        }
    }

}

