package com.example.matuleme2.presentation.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam

@Composable
fun TopBar(controller: NavHostController,textcenter:String,fontWeight: FontWeight, endicon: @Composable ()->Unit) {
    Box {
        Box(modifier = Modifier.fillMaxWidth()) {
            iconback(controller)
            Text(
                textcenter,
                fontFamily = textfam,
                fontWeight = fontWeight,
                color = text,
                modifier = Modifier.align(Alignment.Center)
            )

            endicon()
        }
    }
}
