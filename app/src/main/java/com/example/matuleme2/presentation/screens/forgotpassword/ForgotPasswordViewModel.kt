package com.example.matuleme2.presentation.screens.forgotpassword

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme2.R
import com.example.matuleme2.data.states.ForgatePasswordState
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.textfam
import com.example.matuleme2.presentation.validation.isEmailValid
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.lang.reflect.Modifier
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    ViewModel() {

    private val _state = mutableStateOf(ForgatePasswordState())
    val state: ForgatePasswordState get() = _state.value

    fun updatestate(newstate: ForgatePasswordState) {
        _state.value = newstate
    }

    //Создала для себя, нет отправки кода. сделано чтобы проверить работоспособность уведомления
    var LetterSended: Boolean = true

    //отправка otp если забыл пароль
    fun ForgotPasswordOTP(controller: NavHostController) {
        viewModelScope.launch {
            try {
                if (state.email.isEmailValid()) {
                    //Доделать нажатие кнопки

//                    Constants.supabase.auth.signInWith(OTP){
//                        email   = state.email
//                    }
//                    Constants.supabase.auth.verifyEmailOtp(
//                        email   = state.email
//                    )
                    Log.d("forgot password success", "супер forgot password")
                } else {
                    Log.d("forgot password", "плохо")
                    Toast.makeText(context, "Неверный формат почты", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {

            }
        }
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: Painter,
) {
    AlertDialog(
        icon = {
            Box(modifier = androidx.compose.ui.Modifier
                .clip(CircleShape)
                .size(44.dp)
                .background(accent)
                .clickable {

                })
            {
                Icon(
                    painter = painterResource(R.drawable.email),
                    tint = Color.White,
                    contentDescription = "",
                    modifier = androidx.compose.ui.Modifier.align(Alignment.Center)
                )
            }
        },
        title = {
            Text(
                text = dialogTitle,
                color = Color.Black,
                fontFamily = textfam,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = dialogText, color = Color.Gray,
                fontFamily = textfam,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {},
        dismissButton = {}
    )
}