package com.example.matuleme2.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.matuleme2.R
import com.example.matuleme2.presentation.screens.SignUp.SignUpView
import com.example.matuleme2.presentation.ui.theme.background


@Preview
@Composable
fun Preview() {
    iconback()
}

@Composable
fun iconback() {
        Box(modifier = Modifier
            .clip(CircleShape)
            .size(44.dp)
            .background(background)
            .clickable {}, contentAlignment = Alignment.TopStart
        )
        {
            Icon(
                painter = painterResource(R.drawable.back),
                contentDescription = "", modifier = Modifier.align(Alignment.Center)
            )
        }
}