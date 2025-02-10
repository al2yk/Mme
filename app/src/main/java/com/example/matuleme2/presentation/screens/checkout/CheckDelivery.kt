package com.example.matuleme2.presentation.screens.checkout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CheckDelivery(TextInBox: String, ColorTextMoney: Color, ColorText: Color) {

    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            TextInBox,
            fontWeight = FontWeight.W600,
            color = ColorText,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        Text(
            "сумма",
            color = ColorTextMoney,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
        /*Text("₽${String.format("%.2f", "36,0")}")*/
    }
}