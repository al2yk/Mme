package com.example.matuleme2.presentation.screens.otpcheck.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.matuleme2.presentation.screens.otpcheck.OTPCheckViewModel
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.subtextdark
import com.example.matuleme2.presentation.ui.theme.textfam
import kotlinx.coroutines.delay

@Composable
fun CountdownTimer(
    initialTimeInSeconds: Int = 60, controller: NavHostController, email: String
) {

    val vm = viewModel { OTPCheckViewModel(controller) }
    var currentTime by remember { mutableStateOf(initialTimeInSeconds) }
    val formattedTime = String.format("%02d:%02d", currentTime / 60, currentTime % 60)
    var textColorSendAgain by remember { mutableStateOf(subtextdark) }

    LaunchedEffect(key1 = currentTime) {
        if (currentTime > 0) {
            delay(1000L)
            currentTime--
        } else {
            textColorSendAgain = accent
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            "Отправить заново",
            color = textColorSendAgain,
            fontSize = 12.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable {
                    if (textColorSendAgain == accent) {
                        //Когда текст синий кнопка доступка - можно отправить код повторно
                        textColorSendAgain = subtextdark
                        vm.sendOtpAgain(email)
                        //Проблема тут
                    } else {

                    }

                }
        )
        Text(
            text = formattedTime, color = subtextdark,
            fontSize = 12.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}