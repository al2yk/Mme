package com.example.matuleme2.presentation.screens.main.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme2.R
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.example.matuleme2.presentation.screens.main.popular.components.WhiteButtonMainScreen
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.block
import com.example.matuleme2.presentation.ui.theme.hint
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam


@Preview
@Composable
fun Preview() {
    MainScreen(rememberNavController())
}

@Composable
fun MainScreen(controller: NavHostController) {

    val vm = viewModel { mainviewmodel() }
    val state = vm.state


    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 60.dp)
            .fillMaxSize()
    ) {


        //Верхняя шапка

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.menu),
                contentDescription = "",
                modifier = Modifier.align(Alignment.CenterStart).clickable {
                    controller.navigate(NavigationRoutes.SIDEMENU)
                }
            )

            //Доделать иконку над буквой "Г"
            //Icon(painter = painterResource(R.drawable.welcome1), contentDescription = "", modifier = Modifier.size(20.dp).align(Alignment.Center))

            Text(
                "Главная",
                fontSize = 32.sp,
                fontFamily = textfam,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )

            //Белая кнопка с корзиной
            Box(modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .size(44.dp)
                .background(Color.White)
                .clickable {

                })
            {
                Icon(
                    painter = painterResource(R.drawable.bag),
                    contentDescription = "", modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))


        //Поиск + синяя кнопка с фильтрами?
        Box(modifier = Modifier.fillMaxWidth())
        {
            TextField(
                value = state.search,
                onValueChange = { vm.updatestate(state.copy(search = it)) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(end = 80.dp)
                    .height(52.dp)
                    .shadow(3.dp, shape = RoundedCornerShape(15.dp)),
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
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.searchicon),
                        contentDescription = "",
                        modifier = Modifier.padding(start = 25.dp)
                    )
                }, placeholder = {
                    Text(
                        "Поиск",
                        color = hint,
                        fontSize = 16.sp,
                        fontFamily = textfam,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            )
            Box(modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .size(52.dp)
                .background(accent)
                //Спросить про тень у контейнера
//              .shadow(1.dp, shape = CircleShape, clip = true)
                .clickable {

                })
            {
                Icon(
                    painter = painterResource(R.drawable.filters),
                    contentDescription = "",
                    modifier = Modifier.align(Alignment.Center),
                    tint = Color.White
                )
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        //Категории
        Box(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(
                    "Категории", fontSize = 16.sp,
                    fontFamily = textfam,
                    fontWeight = FontWeight.Bold,
                    color = text
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    WhiteButtonMainScreen("Все")
                    Spacer(modifier = Modifier.width(16.dp))
                    WhiteButtonMainScreen("Outdoor")
                    Spacer(modifier = Modifier.width(16.dp))
                    WhiteButtonMainScreen("Tennis")
                    Spacer(modifier = Modifier.width(16.dp))
                    WhiteButtonMainScreen("Running")
                }

            }


        }
        Spacer(modifier = Modifier.height(30.dp))

        // Популярные
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Популярное", modifier = Modifier.align(Alignment.TopStart), fontSize = 16.sp,
                fontFamily = textfam,
                fontWeight = FontWeight.Medium, color = text
            )
            Text(
                "Все", modifier = Modifier.align(Alignment.BottomEnd), fontSize = 12.sp,
                fontFamily = textfam,
                fontWeight = FontWeight.Medium, color = accent
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        //У поиска изменить кликабельность нужна ещё одна страничка отдельная чтобы только там искать
        Box(modifier = Modifier
            .weight(0.5f)
            .background(block)
        ) {
            Text("Test")
        }


    }

}
