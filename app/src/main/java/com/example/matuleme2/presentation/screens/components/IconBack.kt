package com.example.matuleme2.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.matuleme2.R
import com.example.matuleme2.presentation.ui.theme.background


//@Preview
//@Composable
//fun Preview() {
//    iconback()
//}

@Composable
fun iconback(controller: NavHostController) {
        Box(modifier = Modifier
            .clip(CircleShape)
            .size(44.dp)
            .background(background)
            .clickable {
                //ДОПИСАТЬ ТО ЧТО, ЕСЛИ ПРЕДЫДУЩАЯ СПЛЭШ - НЕ ВОЗВРАЩАТЬСЯ
                controller.navigateUp()
            }, contentAlignment = Alignment.TopStart
        )
        {
            Icon(
                painter = painterResource(R.drawable.back),
                contentDescription = "", modifier = Modifier.align(Alignment.Center)
            )
        }
}