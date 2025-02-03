package com.example.matuleme2.presentation.screens.main.components

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
import com.example.matuleme2.data.models.Category
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.block
import com.example.matuleme2.presentation.ui.theme.subtextdark
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam

//Отризованный белый контейнер под надписью Категории
@Composable
fun WhiteButtonMainScreen(category: Category, isSelected: Boolean, moveTo: (Category) -> Unit) {

    Button(
        onClick = { moveTo(category) },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = block,
            containerColor = if(!isSelected) block else accent
        ), modifier = Modifier
            .height(40.dp)
            .width(100.dp)
    ) {
        Text(
            category.category,
            fontSize = 12.sp,
            color = if(!isSelected) text else background,
            fontFamily = textfam,
            fontWeight = FontWeight.Normal
        )
    }
}