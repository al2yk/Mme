package com.example.matuleme2.presentation.screens.profile.editprofile

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme2.R
import com.example.matuleme2.domain.repository.UserRepository
import com.example.matuleme2.presentation.screens.components.TextNearTextFieldInProfile
import com.example.matuleme2.presentation.screens.components.iconback
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam

@Preview()
@Composable
fun Preview() {
    EditProfileView(rememberNavController())
}

@Composable
fun EditProfileView(controller: NavHostController) {

    val vm = viewModel { EditProfileViewModel() }
    val state = vm.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        //контейнер с кнопкой назад и "Профиль"
        Box(modifier = Modifier.fillMaxWidth()) {
            iconback(controller)
            Text(
                "Профиль",
                fontFamily = textfam,
                fontWeight = FontWeight.SemiBold,
                color = text,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(50.dp))

        LazyColumn() {
            item {
                //Контейнер с фоткой профиля
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillParentMaxWidth()){
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(96.dp)
                            .background(Color.White)

                    ){}
                }


                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        state.name,
                        fontFamily = textfam,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = text
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        state.surname,
                        fontFamily = textfam,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = text
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillParentMaxWidth()) {
                    Text(
                        "Изменить фото профиля",
                        fontFamily = textfam,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = accent, modifier = Modifier
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                //Контейнер с штрих кодом
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(15.dp))
                        .background(background)
                        .height(65.dp)
                )
                {
                    Text(
                        "Открыть", fontFamily = textfam,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp, modifier = Modifier
                            .rotate(-90f)
                            .align(Alignment.CenterStart)
                    )

                    //Добавить сюда штрих код

                }

                TextNearTextFieldInProfile("Имя")
                TextFieldForEditProfile(state.name) { vm.updatestate(state.copy(name = it)) }

                TextNearTextFieldInProfile("Фамилия")
                TextFieldForEditProfile(state.surname) { vm.updatestate(state.copy(surname = it)) }

                TextNearTextFieldInProfile("Адрес")
                TextFieldForEditProfile(state.address) { vm.updatestate(state.copy(address = it)) }


                //Переделать
                TextNearTextFieldInProfile("Телефон")
                TextFieldForEditProfile(state.telephone) {
                    vm.updatestate(state.copy(telephone = it)) }
                Spacer(modifier = Modifier.height(100.dp))

            }
        }


    }

}

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
                    vm.EditProfileData(value)
                }
            )
        }
    )
}