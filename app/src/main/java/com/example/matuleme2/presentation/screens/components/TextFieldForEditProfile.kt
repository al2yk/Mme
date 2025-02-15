package com.example.matuleme2.presentation.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.matuleme2.R
import com.example.matuleme2.presentation.screens.profile.editprofile.EditProfileViewModel
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.text

@Composable
fun TextFieldForEditProfile(value: String, onvaluechange: (String) -> Unit) {

    val vm = viewModel { EditProfileViewModel() }
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
            disabledIndicatorColor = Color.Transparent,
            disabledContainerColor = background,
            disabledTextColor = text
        ),
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        trailingIcon = {
            //Написать условие появления этих галочек
            Icon(
                painter = painterResource(R.drawable.yesicon),
                contentDescription = "",
                tint = accent,
                modifier = Modifier.clickable {
                    vm.updateProfile()
                }
            )
        }
    )
}