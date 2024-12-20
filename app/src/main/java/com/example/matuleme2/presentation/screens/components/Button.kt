package com.example.matuleme2.presentation.screens.components

import android.widget.Button
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.matuleme2.presentation.screens.signup.SingUpViewModel
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.disable
import com.example.matuleme2.presentation.ui.theme.textfam

@Composable
fun ButtonExit(buttontext: String, onclick: () -> Unit) {

    Button(
        onClick = {
            onclick()
        },
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = background,
            containerColor = accent
        ), modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
    ) {
        Text(
            buttontext,
            fontSize = 14.sp,
            color = background,
            fontFamily = textfam,
            fontWeight = FontWeight.W600
        )
    }
}

@Composable
fun ButtomWithCheckBox(buttontext: String, onclick: () -> Unit,) {

    val viewmodel = viewModel { SingUpViewModel() }
    val state = viewmodel.state
    Button(
        onClick = {
            onclick()
        },
        shape = RoundedCornerShape(15.dp),
        enabled = state.checkBox,
        colors = ButtonDefaults.buttonColors(
            contentColor = background,
            containerColor = accent,
            disabledContainerColor = disable,
            disabledContentColor = background
        ), modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
    ) {
        Text(
            buttontext,
            fontSize = 14.sp,
            color = background,
            fontFamily = textfam,
            fontWeight = FontWeight.W600
        )
    }
}