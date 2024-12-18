package com.example.matuleme2.presentation.screens.Matuleme.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.matuleme2.presentation.screens.components.gradient
import com.example.matuleme2.presentation.ui.theme.block
import com.example.matuleme2.presentation.ui.theme.textfam


@Composable
fun splashscreen(controller: NavHostController) {
    val VM = viewModel { splashviewmodel() }

    LaunchedEffect(Unit) {
        VM.launch(controller)
    }

    Box(modifier = Modifier
        .background(gradient.gradient)
        .fillMaxSize())
    {
        Row(modifier = Modifier.align(Alignment.Center)) {
            Text(
                "MATULE",
                fontSize = 49.sp,
                fontFamily = textfam,
                fontWeight = FontWeight.Bold,
                color = block,
                modifier = Modifier.align(Alignment.Top)
            )
            Text(
                "ME", fontSize = 25.sp,
                fontFamily = textfam,
                fontWeight = FontWeight.ExtraLight,
                color = block,
            )
        }

    }

}