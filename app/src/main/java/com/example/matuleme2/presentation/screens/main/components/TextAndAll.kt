package com.example.matuleme2.presentation.screens.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.matuleme2.data.models.Category
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam

@Composable
fun TextAndAll(isText: String, weightfont: FontWeight, controller: NavHostController, onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            isText, modifier = Modifier.align(Alignment.TopStart), fontSize = 16.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.Medium, color = text
        )
        Text(
            "Все", modifier = Modifier
                .align(Alignment.BottomEnd)
                .clickable {
                    onClick()
                    /* controller.navigate(NavigationRoutes.POPULAR)*/
                }, fontSize = 12.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.Medium, color = accent
        )
    }
}