package com.example.matuleme2.presentation.screens.main.popular.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.block
import com.example.matuleme2.presentation.ui.theme.subtextdark
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam

@Composable
fun WhiteButtonMainScreen(word:String){
    Button(
        onClick = {},
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = block,
            containerColor = block
        ), modifier = Modifier
            .height(40.dp)
            .width(100.dp)
    ) {
        Text(
            word,
            fontSize = 12.sp,
            color = text,
            fontFamily = textfam,
            fontWeight = FontWeight.Normal
        )
    }
}