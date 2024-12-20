package com.example.matuleme2.presentation.screens.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matuleme2.presentation.ui.theme.subtextdark
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam


@Composable
fun TextNearTextFieldSIGNINSIGNUP(TextNear:String){

    Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))

    Text(TextNear, fontFamily = textfam,
        fontWeight = FontWeight.Medium,
        color = text, fontSize = 16.sp)

    Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))

}

@Composable
fun TextNearTextFieldInProfile(TextNear:String){

    Spacer(modifier = androidx.compose.ui.Modifier.height(15.dp))

    Text(TextNear, fontFamily = textfam,
        fontWeight = FontWeight.SemiBold,
        color = text, fontSize = 16.sp)

    Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))

}

@Composable
fun TextNearTextFieldEditProfile(TextNear:String){

    Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))

    Text(TextNear, fontFamily = textfam,
        fontWeight = FontWeight.SemiBold,
        color = subtextdark, fontSize = 16.sp)
    Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))

}