package com.example.matuleme2.presentation.screens.SignUp

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.example.matuleme2.presentation.screens.components.ButtonExit
import com.example.matuleme2.presentation.screens.components.TextFieldSignInEmail
import com.example.matuleme2.presentation.screens.components.iconback
import com.example.matuleme2.presentation.screens.components.TextFieldSignInPass
import com.example.matuleme2.presentation.screens.components.TextNearTextField
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.hint
import com.example.matuleme2.presentation.ui.theme.subtextdark
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam

@Preview()
@Composable
fun Preview() {
    SignUpView(rememberNavController())
}

@Composable
fun SignUpView(controller: NavHostController) {
    val viewmodel = viewModel { SignUpViewModel() }
    val state = viewmodel.state
    val context = LocalContext.current

//    LaunchedEffect(Unit) {
//        viewmodel.context = context
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        iconback()
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Регистрация",
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
        TextNearTextField("Ваше имя")
        TextFieldSignInEmail(state.name, "xxxxxxxx") {
            viewmodel.updatestate(state.copy(name = it))
        }

        TextNearTextField("Email")
        TextFieldSignInEmail(state.email, "xyz@gmail.com") {
            viewmodel.updatestate(state.copy(email = it))
        }

        TextNearTextField("Пароль")
        TextFieldSignInPass(state.password, "● ● ● ● ● ● ●") {
            viewmodel.updatestate(state.copy(password = it))
        }


        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.fillMaxWidth())
        {
            Row {
                Box(modifier = Modifier
                    .clip(CircleShape)
                    .size(18.dp)
                    .background(background)
                    .clickable {}
                    .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    "Даю согласие на обработку персональных данных",
                    textDecoration = TextDecoration.Underline,
                    color = hint,
                    fontFamily = textfam,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(end = 30.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        ButtonExit(viewmodel.signUp(controller, context).toString(), "Зарегистрироваться")

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Row {
                Text(
                    "Есть Аккаунт?", color = hint,
                    fontFamily = textfam,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.width(5.dp))

                Text("Войти", color = text,
                    fontFamily = textfam,
                    fontWeight = FontWeight.Medium, modifier = Modifier.clickable {
                        controller.navigate(NavigationRoutes.SIGNIN)})
            }
        }


    }
}
