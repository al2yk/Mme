package com.example.matuleme2.presentation.screens.forgotpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.matuleme2.presentation.screens.components.ButtonExit
import com.example.matuleme2.presentation.screens.components.TextFieldSignInEmail
import com.example.matuleme2.presentation.screens.components.iconback
import com.example.matuleme2.presentation.ui.theme.subtextdark
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam
import androidx.compose.ui.res.painterResource
import androidx.navigation.Navigation
import com.example.matuleme2.R
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.example.matuleme2.presentation.screens.forgotpassword.components.AlertDialogExample
import com.example.matuleme2.presentation.ui.theme.hint

@Composable
fun ForgotPasswordView(
    controller: NavHostController,
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
) {


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
            "Забыл пароль",
            fontSize = 32.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.Bold,
            color = text,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            "Введите свою учетную запись для сброса", fontSize = 16.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.Normal,
            color = subtextdark,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 35.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        TextFieldSignInEmail(state.email, "xyz@gmail.com", true, hint) {
            viewModel.updatestate(state.copy(email = it))
        }
        Spacer(modifier = Modifier.height(40.dp))
        ButtonExit("Отправить") {
            viewModel.ForgotPasswordOTP(controller)
        }

        if (state.dialog == true) {
            AlertDialogExample(
                onDismissRequest =
                {
                    viewModel.updatestate(state.copy(dialog = false))
                    controller.navigate(NavigationRoutes.OTP + "/${state.email}")
                },
                onConfirmation = {
                    state.dialog = false
                    println("Confirmation registered")
                },
                dialogTitle = "Проверьте Ваш Email",
                dialogText = "Мы отправили код восстановления пароля на вашу электронную почту.",
                icon = painterResource(R.drawable.checkemailicon)
            )
        }
    }

}

//AlertDialogExample(
//onDismissRequest = { state.dialog = false },
//onConfirmation = {
//    state.dialog = false
//    println("Confirmation registered")
//},
//dialogTitle = "Проверьте Ваш Email",
//dialogText = "Мы отправили код восстановления пароля на вашу электронную почту.",
//icon = painterResource(R.drawable.checkemailicon)
//)