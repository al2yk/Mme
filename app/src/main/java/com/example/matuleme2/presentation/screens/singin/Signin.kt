package com.example.matuleme2.presentation.screens.singin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme2.presentation.ui.theme.subtextdark
import com.example.matuleme2.presentation.ui.theme.textfam
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.example.matuleme2.presentation.screens.components.ButtonExit
import com.example.matuleme2.presentation.screens.components.TextFieldSignInEmail
import com.example.matuleme2.presentation.screens.components.TextFieldSignInPass
import com.example.matuleme2.presentation.screens.components.TextNearTextField
import com.example.matuleme2.presentation.screens.components.iconback
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.hint
import com.example.matuleme2.presentation.ui.theme.text

@Preview
@Composable
fun Preview() {
    Signin(rememberNavController())
}

@Composable
fun Signin(controller: NavHostController) {

    //объявление viewmodel
    val viewmodel = viewModel { singinviewmodel() }
    val state = viewmodel.state
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        iconback()
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
        TextNearTextField("Email")
        TextFieldSignInEmail(state.email, "xyz@gmail.com") {
            viewmodel.updatestate(state.copy(email = it))
        }

        TextNearTextField("Пароль")
        TextFieldSignInPass(state.password, "● ● ● ● ● ● ●") {
            viewmodel.updatestate(state.copy(password = it))
        }
        Spacer(modifier = Modifier.height(30.dp))
        ButtonExit(viewmodel.signIn(controller,context).toString(), "Войти")

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Row {
                Text(
                    "Вы впервые?", color = hint,
                    fontFamily = textfam,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.width(5.dp))

                Text("Создать пользователя", color = text,
                    fontFamily = textfam,
                    fontWeight = FontWeight.Medium, modifier = Modifier.clickable {
                        controller.navigate(NavigationRoutes.SIGNUP)})
            }
        }


    }


//    LaunchedEffect(Unit) {
//        viewmodel.context = context
//    }
//
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .padding(vertical = 100.dp)
//        .padding(horizontal = 20.dp) ) {
//
//        Box(modifier = Modifier
//            .align(Alignment.CenterHorizontally)
//            .padding(horizontal = 0.dp))
//        {
//                Column(modifier = Modifier.align(Alignment.Center)) {
//                    Text("Привет!", modifier = Modifier.align(Alignment.CenterHorizontally),
//                        fontFamily = textfam,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 32.sp)
//                    Spacer(modifier = Modifier.height(10.dp))
//                    Text("Заполните Свои данные или продолжите через социальные медиа",
//                        textAlign = TextAlign.Center
//                        ,fontFamily = textfam,
//                        fontWeight = FontWeight.Normal,
//                        color = subtextdark,
//                        fontSize = 18.sp,
//                        modifier = Modifier.padding(horizontal = 10.dp)) }
//        }
//        Spacer(modifier = Modifier.height(30.dp))
//
//        //Контейнер где поля эмейла и пароля , кнопка
//        Box(modifier = Modifier
//            .align(Alignment.CenterHorizontally)
//            .fillMaxWidth())
//        {       Column(modifier = Modifier
//                .align(Alignment.TopStart)
//                .fillMaxSize()) {
//            Text("Email", fontFamily = textfam, fontWeight = FontWeight.W600, fontSize = 16.sp)
//            Spacer(modifier = Modifier.height(15.dp))
//            TextFieldSignInEmail(state.email, "xyz@gmail.com") {
//                viewmodel.updatestate(state.copy(email = it))
//            }
//
//            Spacer(modifier = Modifier.height(30.dp))
//
//            Text("Пароль", fontFamily = textfam, fontWeight = FontWeight.W600, fontSize = 16.sp)
//            Spacer(modifier = Modifier.height(15.dp))
//            TextFieldSignInPass(state.password, "● ● ● ● ● ● ●") {
//                viewmodel.updatestate(state.copy(password = it))
//            }
//
//            Spacer(modifier = Modifier.height(5.dp))
//
//            Text(
//                "Восстановить",
//                fontFamily = textfam,
//                fontWeight = FontWeight.Normal,
//                fontSize = 12.sp,
//                color = subtextdark,
//                modifier = Modifier.align(Alignment.End)
//            )
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Button(
//                onClick = {
//                    viewmodel.signIn(controller)
//                },
//                shape = RoundedCornerShape(15.dp),
//                colors = ButtonDefaults.buttonColors(
//                    contentColor = background,
//                    containerColor = accent
//                ), modifier = Modifier
//                    .fillMaxWidth()
//                    .height(55.dp)
//            ) {
//                Text(
//                    "Войти",
//                    fontSize = 14.sp,
//                    color = background,
//                    fontFamily = textfam,
//                    fontWeight = FontWeight.W600
//                )
//            }
//        }
//                Box(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 0.dp)) {
//                    Row(modifier = Modifier.align(Alignment.Center)) {
//                        Text(
//                            "Вы впервые?",
//                            fontSize = 16.sp,
//                            fontFamily = textfam,
//                            fontWeight = FontWeight.Medium,
//                            color = hint
//                        )
//                        Text(
//                            " Создать пользователя",
//                            fontSize = 16.sp,
//                            fontFamily = textfam,
//                            fontWeight = FontWeight.Medium,
//                            color = text
//                        )
//
//                    }
//                }
//            }
//        }
    }
