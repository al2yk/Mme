package com.example.matuleme2.presentation.screens.otpcheck

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme2.presentation.screens.components.iconback
import com.example.matuleme2.presentation.screens.otpcheck.components.CountdownTimer
import com.example.matuleme2.presentation.screens.otpcheck.components.InputCode
import com.example.matuleme2.presentation.ui.theme.subtextdark
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam

@Preview()
@Composable
fun Preview() {
    OTPCheckView(rememberNavController(), "")
}

//Верстка экрана
@Composable
fun OTPCheckView(controller: NavHostController, email: String) {

    LaunchedEffect(Unit) {
        Log.d("email", email)
    }

    val vm = viewModel { OTPCheckViewModel(controller) }
    var state = vm.state

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

        //6 ячеек для ввода отп
        InputCode(state.ColorBoard,vm, email)


        Spacer(modifier = Modifier.height(20.dp))

        //Таймер
        CountdownTimer(60,controller, email)
    }
}