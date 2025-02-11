package com.example.matuleme2.presentation.screens.main

import android.util.Log
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.matuleme2.presentation.screens.components.iconback
import com.example.matuleme2.presentation.screens.components.BagWithRed
import com.example.matuleme2.presentation.screens.components.Heart
import com.example.matuleme2.presentation.screens.components.TopBar
import com.example.matuleme2.presentation.screens.main.components.RowCategory
import com.example.matuleme2.presentation.screens.main.components.SneakerItem
import com.example.matuleme2.presentation.screens.main.components.TextAndAll
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

    LaunchedEffect(Unit) {
        vm.getData()
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 60.dp)
            .fillMaxSize()
    ) {
        when (state.viewState) {
            //Главный экран
            MainScreenViewState.Main -> {

                //Верхняя шапка
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.menu),
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .clickable {
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

                    //Корзина сверху
                    BagWithRed()
                }

                Spacer(modifier = Modifier.height(19.dp))

                //Поиск + синяя кнопка с фильтрами?
                Box(modifier = Modifier.fillMaxWidth())
                {

                    //Поиск -> при клике переходим на страницу поиска
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 70.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .shadow(3.dp)
                        .background(block)
                        .align(Alignment.TopCenter)
                        .clickable {
                            controller.navigate(NavigationRoutes.SEARCH)
                        }
                        .height(52.dp), contentAlignment = Alignment.CenterStart)
                    {
                        Icon(
                            painter = painterResource(R.drawable.searchicon),
                            contentDescription = "",
                            modifier = Modifier.padding(start = 25.dp), tint = hint
                        )

                        Text(
                            "Поиск",
                            color = hint,
                            fontSize = 16.sp,
                            fontFamily = textfam,
                            fontWeight = FontWeight.W600,
                            modifier = Modifier.padding(start = 60.dp)
                        )

                    }

                    //Круг синий с фильтрами?
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

                Spacer(modifier = Modifier.height(24.dp))

                //Категории
                Box(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        RowCategory(state, vm, false) {
                            vm.updatestate(
                                state.copy(
                                    selectedCategory = it,
                                    viewState = MainScreenViewState.Category
                                )
                            )

                        }
                    }

                }

                Spacer(modifier = Modifier.height(24.dp))


                //Популярные     все
                TextAndAll("Популярное", FontWeight.Medium, controller) {
                    vm.updatestate(
                        state.copy(
                            viewState = MainScreenViewState.Popular
                        )
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                //Две пары кросовок
                Row(modifier = Modifier) {
                    LazyVerticalGrid(
                        //Две колонки
                        columns = GridCells.Fixed(2),
                        //Расстояние между элементами
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        items(state.sneakers.filter { it.is_popular }.take(2)) { sneaker ->
                            SneakerItem(
                                sneaker,
                                state.listIdFavSneakers.contains(sneaker.id_sneaker),
                                onClickFav = { vm.clickFavIcon(sneaker) },
                                onClick = {
                                    Log.d("MainScreen", "Sneaker clicked: ${sneaker.id_sneaker}")
                                    controller.navigate("product/${sneaker.id_sneaker}")
                                    Log.d("переход","а")

                                }
                            )

                        }
                    }
                }

                Spacer(modifier = Modifier.height(29.dp))

                //Акции   все. При нажатии нет страницы
                TextAndAll("Акции", FontWeight.SemiBold, controller) {}

                Spacer(modifier = Modifier.height(20.dp))

                //контейнер с акцией
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                )
                {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(R.drawable.sale),
                        contentDescription = "",
                        alignment = Alignment.TopCenter

                    )
                }


            }
            //Экран Категории
            MainScreenViewState.Category -> {

                Box(modifier = Modifier.fillMaxWidth()) {
                    //Иконка назад
                    iconback(controller)
                    Text(
                        state.selectedCategory.category,
                        fontFamily = textfam,
                        fontWeight = FontWeight.Medium,
                        color = text,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                //Категории
                Box(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        RowCategory(state, vm) {
                            vm.updatestate(state.copy(selectedCategory = it))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    var listSneaker =
                        state.sneakers.filter { it.id_category == state.selectedCategory.id_category }
                    if (state.selectedCategory.category == "Все") listSneaker = state.sneakers
                    items(listSneaker) { sneaker ->
                        SneakerItem(
                            sneaker, state.listIdFavSneakers.contains(sneaker.id_sneaker),
                            onClick = { controller.navigate("product/${sneaker.id_sneaker}") },
                            onClickFav = { vm.clickFavIcon(sneaker) })
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))

            }
            //Экран популярные
            MainScreenViewState.Popular -> {
                //контейнер с кнопкой назад "Популярное" и фав

                TopBar(controller, "Популярное", FontWeight.Medium) {
                    Heart() {
                        controller.navigate(
                            NavigationRoutes.FAVOURITE
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                //Вывод кросовок
                Row() {

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        var listSneaker =
                            state.sneakers
                        items(listSneaker) { sneaker ->
                            SneakerItem(
                                sneaker,
                                state.listIdFavSneakers.contains(sneaker.id_sneaker),
                                onClickFav = { vm.clickFavIcon(sneaker) },
                                onClick = { controller.navigate("product/${sneaker.id_sneaker}") })
                        }
                    }
                }


            }
        }
    }
}

sealed class MainScreenViewState(title: String) {
    data object Main : MainScreenViewState("Главное")
    data object Popular : MainScreenViewState("Популярное")
    data object Category : MainScreenViewState("Категории")
}