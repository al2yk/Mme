package com.example.matuleme2.presentation.screens.sidemenu

import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.matuleme2.R
import com.example.matuleme2.data.models.User
import com.example.matuleme2.domain.Constants
import com.example.matuleme2.domain.repository.UserRepository
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.block
import com.example.matuleme2.presentation.ui.theme.prof
import com.example.matuleme2.presentation.ui.theme.red
import com.example.matuleme2.presentation.ui.theme.textfam
import io.github.jan.supabase.gotrue.auth


@Preview()
@Composable
fun Preview() {
//    SideMenuView(rememberNavController())
}

@Composable
fun SideMenuView(controller: NavHostController) {

//    val viewModel = viewModel { SideMenuViewModel() }
//    val state = viewModel.state

    val viewModel = viewModel { SideMenuViewModel()}
    val state = viewModel.state
    val user:User


    Box(modifier = Modifier.background(accent)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 60.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier.padding(horizontal = 15.dp)) {

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(96.dp)
                        .background(Color.White)
                )
                {
                    //Тут будет фотка профиля
                }

                //Добавить пути по иконкам
                Spacer(modifier = Modifier.height(25.dp))
                Row {
                    TextNameSurname("")
                    Spacer(modifier = Modifier.width(5.dp))

                }
                Spacer(modifier = Modifier.height(55.dp))
                IconAndText(painterResource(R.drawable.profile), "Профиль"){
                    controller.navigate(NavigationRoutes.BASICPROFILE)
                }
                Spacer(modifier = Modifier.height(30.dp))
                IconAndText(painterResource(R.drawable.bag), "Корзина"){}
                Spacer(modifier = Modifier.height(30.dp))
                IconAndText(painterResource(R.drawable.favprofile), "Избранное"){}
                Spacer(modifier = Modifier.height(30.dp))
                IconAndText(painterResource(R.drawable.orders), "Заказы"){}
                Spacer(modifier = Modifier.height(30.dp))
                IconAndTextForNotification(painterResource(R.drawable.notification), "Уведомления","")
                Spacer(modifier = Modifier.height(30.dp))
                IconAndText(painterResource(R.drawable.setings), "Настройки"){}
                Spacer(modifier = Modifier.height(30.dp))

                Spacer(modifier = Modifier.height(40.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(prof)
                        .height(1.dp)
                )
                val currentUser = Constants.supabase.auth.currentUserOrNull()
                Log.d("curUser", currentUser.toString())


                //реализовать выход из аккаунта

                Spacer(modifier = Modifier.height(30.dp))
                IconAndText(painterResource(R.drawable.exit), "Выйти") {
                    UserRepository.act = 1
                    controller.navigate(NavigationRoutes.SIGNIN) {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                }
            }

        }
    }

}

@Composable
fun TextNameSurname(title: String) {
    Text(
        title, color = block,
        fontSize = 20.sp,
        fontFamily = textfam,
        fontWeight = FontWeight.Bold
    )
}

//controller: NavHostController
@Composable
fun IconAndText(icon: Painter, title: String, way: () -> Unit) {


    //ДОБАВИТЬ ПРО УВЕДОМЛЕНИЯ, ЕСЛИ ОНИ ЕСТЬ ТО У ИКОНКИ УВЕДОМЛЕНИЯ
    //ДОБАВЛЯЕТСЯ КРАСНЫЙ КРУЖОЧЕК
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {way()},
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        ) {


        Icon(
            painter = icon, tint = block,
            contentDescription = "", modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(25.dp))

        Text(
            title, color = block,
            fontSize = 16.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun IconAndTextForNotification(icon: Painter, title: String, way: String) {


    //ДОБАВИТЬ ПРО УВЕДОМЛЕНИЯ, ЕСЛИ ОНИ ЕСТЬ ТО У ИКОНКИ УВЕДОМЛЕНИЯ
    //ДОБАВЛЯЕТСЯ КРАСНЫЙ КРУЖОЧЕК??
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                way
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(

        ) {
            Icon(
                painter = icon, tint = block,
                contentDescription = "", modifier = Modifier.size(24.dp)
            )
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 3.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ellipse), tint = red,
                   contentDescription = "", modifier = Modifier
                        .size(8.dp)
                        .align(Alignment.TopEnd)
                )
            }

        }

        Spacer(modifier = Modifier.width(25.dp))

        Text(
            title, color = block,
            fontSize = 16.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.Medium
        )
    }
}

