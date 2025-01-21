package com.example.matuleme2.presentation.screens.otpcheck

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme2.domain.Constants
import com.example.matuleme2.presentation.screens.components.iconback
import com.example.matuleme2.presentation.screens.forgotpassword.ForgotPasswordView
import com.example.matuleme2.presentation.screens.forgotpassword.ForgotPasswordViewModel
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.subtextdark
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@Preview()
@Composable
fun Preview() {
    OTPCheckView(rememberNavController())
}

@Composable
fun OTPCheckView(controller: NavHostController) {


    val vm = viewModel { OTPCheckViewModel(controller) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {

        iconback(controller)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "ОТР Проверка",
            fontSize = 32.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.Bold,
            color = text,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Пожалуйста, Проверьте Свою Электронную Почту, Чтобы Увидеть Код Подтверждения",
            fontSize = 16.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.Normal,
            color = subtextdark,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 30.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "ОТР Код", fontSize = 21.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.SemiBold,
            color = text,
            modifier = Modifier
                .align(Alignment.Start),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        ContentView(
            textList = vm.textlist,
            requesteList = vm.requesterList,
            vm, controller
        )

        Spacer(modifier = Modifier.height(20.dp))


        CountdownTimer(60,controller)
    }
}
/*

@Composable
fun CountdownTimerScreen(controller: NavHostController) {
    var countDown by remember { mutableStateOf(60) }
    var buttonEnabled by remember { mutableStateOf(true) }
    var buttonColor by remember { mutableStateOf(Color.Gray) }
    val vm = viewModel { OTPCheckViewModel(controller) }
    val animatedButtonColor by animateColorAsState(targetValue = buttonColor)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = formatTime(countDown),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Text(
                text = "Отправить заново",
                color = animatedButtonColor,
                modifier = Modifier
                    .clickable(enabled = buttonEnabled) {
                        if (buttonEnabled) {
                            vm.onClickSendAgain()
                            countDown = 60
                            buttonEnabled = false
                            buttonColor = Color.Gray
                        }
                    }
                    .padding(vertical = 12.dp, horizontal = 24.dp)
            )
        }
    }

    if (countDown > 0 && buttonEnabled) {
        LaunchedEffect(Unit) {
            while (countDown > 0) {
                delay(TimeUnit.SECONDS.toMillis(1))
                countDown -= 1
            }
            buttonEnabled = true
            buttonColor = Color.Blue
        }
    }
}

private fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return "%02d:%02d".format(minutes, remainingSeconds)
}
*/


@Composable
fun CountdownTimer(
    initialTimeInSeconds: Int = 60,controller: NavHostController
) {


    val vm = viewModel { OTPCheckViewModel(controller) }
    val scope = rememberCoroutineScope()
    val state = vm.state
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
                        textColorSendAgain = subtextdark
                        vm.ForgotPasswordOTP(controller)
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
