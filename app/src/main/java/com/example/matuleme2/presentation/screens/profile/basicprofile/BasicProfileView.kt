package com.example.matuleme2.presentation.screens.profile.basicprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.matuleme2.R
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.example.matuleme2.presentation.screens.components.ButtonExit
import com.example.matuleme2.presentation.screens.components.TextFieldSignInEmail
import com.example.matuleme2.presentation.screens.components.TextNearTextFieldEditProfile
import com.example.matuleme2.presentation.screens.components.iconback
import com.example.matuleme2.presentation.screens.profile.editprofile.EditProfileViewModel
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.hint
import com.example.matuleme2.presentation.ui.theme.subtextdark
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam

@Preview()
@Composable
fun Preview() {
    BasicProfileView(rememberNavController())
}

@Composable
fun BasicProfileView(controller: NavHostController) {

    val vm = viewModel{ EditProfileViewModel() }
    val state = vm.state

    LaunchedEffect(Unit) {
        vm.loadProfileData()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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

        //СДЕЛАТЬ ПЕРЕКЛЮЧЕНИЕ ПО СИНЕЙ КНОПКЕ
        //Контейнер с фоткой профиля и кнопкой чтобы редактировать профиль
        Box(contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp)
                    .background(Color.White)
            ){
                val imgState = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.image)
                        .size(Size.ORIGINAL).build()
                ).state
                if (imgState is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator()
                }
                if (imgState is AsyncImagePainter.State.Success) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .clip(RoundedCornerShape(15.dp)),
                        painter = imgState.painter,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Box(contentAlignment = Alignment.Center, modifier = Modifier
                .padding(horizontal = 10.dp)
                .clip(CircleShape)
                .size(20.dp)
                .background(accent)
                .border(1.dp, color = Color.White, RoundedCornerShape(10.dp))
                .clickable {})
            {
                Icon(
                    painter = painterResource(R.drawable.penedit), tint = Color.White,
                    contentDescription = "", modifier = Modifier.clickable { controller.navigate(NavigationRoutes.EDITPROFILE) }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        TextNearTextFieldEditProfile("Ваше имя")
        TextFieldSignInEmail(value = "${state.name} ${state.surname}".uppercase(), "",false, text) {
            vm.updatestate(state.copy(name = it))
        }
        Spacer(modifier = Modifier.height(30.dp))

        TextNearTextFieldEditProfile("Email")
        TextFieldSignInEmail(state.email, state.email,false, text) {
            vm.updatestate(state.copy(email = it))
        }
        Spacer(modifier = Modifier.height(30.dp))
        TextNearTextFieldEditProfile("Пароль")
        TextFieldSignInEmail("", "● ● ● ● ● ● ●",false, hint) {
            vm.updatestate(state.copy(name = it))
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Восстановить пароль",
            fontFamily = textfam,
            fontWeight = FontWeight.Normal,
            color = subtextdark,
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    controller.navigate(NavigationRoutes.FORGOTPASSWORD)
                }
        )
        Spacer(modifier = Modifier.height(35.dp))
        ButtonExit(buttontext = "Сохранить"){
            vm.updateProfile()
        }
    }
}

