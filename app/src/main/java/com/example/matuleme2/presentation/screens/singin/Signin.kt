package com.example.matuleme2.presentation.screens.singin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.example.matuleme2.presentation.screens.components.ButtonExit
import com.example.matuleme2.presentation.screens.components.TextFieldSignInEmail
import com.example.matuleme2.presentation.screens.components.TextFieldSignInPass
import com.example.matuleme2.presentation.screens.components.TextNearTextFieldSIGNINSIGNUP
import com.example.matuleme2.presentation.screens.components.iconback
import com.example.matuleme2.presentation.ui.theme.hint
import com.example.matuleme2.presentation.ui.theme.subtextdark
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam


@Composable
fun Signin(controller: NavHostController, viewModel: SingInViewModel = hiltViewModel()) {

    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        iconback(controller)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Привет!",
            fontSize = 32.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.Bold,
            color = text,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            "Заполните Свои данные или продолжите через социальные медиа", fontSize = 16.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.Normal,
            color = subtextdark,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 25.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))
        TextNearTextFieldSIGNINSIGNUP("Email")
        TextFieldSignInEmail(state.email, "xyz@gmail.com",true, hint) {
            viewModel.updatestate(state.copy(email = it))
        }

        TextNearTextFieldSIGNINSIGNUP("Пароль")
        TextFieldSignInPass(state.password, "● ● ● ● ● ● ●") {
            viewModel.updatestate(state.copy(password = it))
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Восстановить",
            fontFamily = textfam,
            fontWeight = FontWeight.Normal,
            color = subtextdark,
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    controller.navigate(NavigationRoutes.FORGOTPASSWORD)
                }
        )
        Spacer(modifier = Modifier.height(10.dp))
        ButtonExit("Войти") {
            viewModel.signIn(controller).toString()
        }

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                "Вы впервые?", color = hint,
                fontFamily = textfam,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(5.dp))

            Text("Создать пользователя", color = text,
                fontFamily = textfam,
                fontWeight = FontWeight.Medium, modifier = Modifier.clickable {
                    controller.navigate(NavigationRoutes.SIGNUP)
                })
        }


    }

}
