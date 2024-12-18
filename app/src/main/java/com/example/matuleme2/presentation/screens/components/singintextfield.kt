package com.example.matuleme2.presentation.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matuleme2.R
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.hint
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam

//Чтобы скрыть
@Composable
fun TextFieldSignInEmail(value: String, placeholder: String, onvaluechange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = { onvaluechange(it) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = background,
            unfocusedTextColor = text,
            focusedContainerColor = background,
            focusedTextColor = text,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        placeholder = {
            Text(
                placeholder,
                color = hint,
                fontSize = 16.sp,
                fontFamily = textfam,
                fontWeight = FontWeight.W600
            )
        }
    )
}

@Composable
fun TextFieldSignInPass(value: String, placeholder: String, onvaluechange: (String) -> Unit) {

    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = { onvaluechange(it) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = background,
            unfocusedTextColor = text,
            focusedContainerColor = background,
            focusedTextColor = text,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
            
        ),
        //В одну линию
        singleLine = true,
        shape = RoundedCornerShape(15.dp),
        placeholder = {
            Text(
                placeholder,
                color = hint,
                fontSize = 16.sp,
                fontFamily = textfam,
                fontWeight = FontWeight.W600
            )
        },
        //Скрыть или показать что пишем
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), //?
        //для иконка
        trailingIcon = {
            val image = if (passwordVisible)
                painterResource(R.drawable.eye_open)
            else painterResource(R.drawable.eye_close)
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painter = image, "")
            }
        }
    )
}
