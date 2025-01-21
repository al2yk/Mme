package com.example.matuleme2.presentation.screens.main

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
    var isLiked by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        vm.getData(controller)
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 60.dp)
            .fillMaxSize()
    ) {
        when (state.viewState) {
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

                    BagWithRed()
                    /*
                                        //Белая кнопка с корзиной
                                        Box(modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .clip(CircleShape)
                                            .size(44.dp)
                                            .background(Color.White)
                                            .clickable {}
                                        ) {
                                            Icon(
                                                painter = painterResource(R.drawable.bag),
                                                contentDescription = "", modifier = Modifier.align(Alignment.Center)
                                            )
                                            Box(
                                                contentAlignment = Alignment.TopEnd,
                                                modifier = Modifier
                                                    .size(44.dp)
                                                    .padding(end = 2.dp, top = 3.dp)
                                            ) {
                                                Icon(
                                                    painter = painterResource(R.drawable.ellipse), tint = Color.Red,
                                                    contentDescription = "", modifier = Modifier
                                                        .size(8.dp)
                                                        .align(Alignment.TopEnd)
                                                )
                                            }


                                        }*/
                }

                Spacer(modifier = Modifier.height(19.dp))


                //Поиск + синяя кнопка с фильтрами?
                Box(modifier = Modifier.fillMaxWidth())
                {

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 70.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .shadow(3.dp)
                        .background(background)
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
                    /*
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
                    */
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
                        /*Text(
                            "Категории", fontSize = 16.sp,
                            fontFamily = textfam,
                            fontWeight = FontWeight.Bold,
                            color = text
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(state.categories) { category ->
                                WhiteButtonMainScreen(
                                    category,
                                    category == vm.state.selectedCategory
                                ) {//Реализоваать переход на кате}
                                    vm.updatestate(state.copy(selectedCategory = it))
                                    vm.updatestate(state.copy(viewState = MainScreenViewState.Category))
                                }

                            }

                        }*/
                    }

                }
                Spacer(modifier = Modifier.height(24.dp))

                TextAndAll("Популярное", FontWeight.Medium, controller){
                    vm.updatestate(
                        state.copy(
                            viewState = MainScreenViewState.Popular
                        )
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        items(state.sneakers.filter { it.is_popular }.take(2)) { sneaker ->
                            SneakerItem(sneaker)
                            /*Box(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(15.dp))
                                    .background(background)
                                    .padding(top = 3.dp, start = 9.dp)
                            ) {
                                //Иконка фав
                                Box(modifier = Modifier.padding(top = 9.dp, start = 9.dp)) {
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.TopStart)
                                            .clip(CircleShape)
                                            .size(28.dp)
                                            .background(Color.White)
                                            .clickable {

                                            }
                                    ) {
//                                    IconButton(onClick = { isLiked = !isLiked }) {
//                                        val imageVector = if (isLiked) {
//                                            Icons.Filled.Favorite // Иконка заполненного сердца
//                                        } else {
//                                            Icons.Outlined.Favorite // Иконка пустого сердца
//                                        }
//
//                                        Icon(
//                                            imageVector = imageVector,
//                                            contentDescription = "Like",
//                                            tint = if (isLiked) Color.Red else Color.Black
//                                        )
//                                    }
                                        Icon(
                                            painter = painterResource(R.drawable.favprofile),
                                            contentDescription = "",
                                            modifier = Modifier.align(Alignment.Center)
                                        )
                                    }
                                }

                                Column(
                                    modifier = Modifier
                                        .padding(top = 34.dp, bottom = 9.dp)
                                        .padding(horizontal = 9.dp)
                                        .fillMaxHeight()
                                ) {

                                    //загрузка картинки
                                    val imgState = rememberAsyncImagePainter(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(sneaker.image)
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

                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        "BEST SELLER",
                                        fontFamily = textfam,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 12.sp,
                                        color = accent
                                    )
                                    Text(sneaker.small_title)
                                    Spacer(modifier = Modifier.height(14.dp))
                                    Row() {
                                        Text("₽${String.format("%.2f", sneaker.cost)}")
                                        Box(
                                            modifier = Modifier.fillMaxWidth(),
                                            contentAlignment = Alignment.TopEnd
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(34.dp)
                                                    .clip(
                                                        RoundedCornerShape(
                                                            topStart = 15.dp,
                                                            bottomEnd = 15.dp
                                                        )
                                                    )
                                                    .background(accent)
                                            ) {
                                                Icon(
                                                    painter = painterResource(R.drawable.plus),
                                                    contentDescription = "",
                                                    modifier = Modifier.align(Alignment.Center),
                                                    tint = Color.White
                                                )
                                            }
                                        }

                                    }

                                }
                            }*/
                        }
                    }
                    /*LazyColumn {
                        items(state.sneakers) { sneaker ->
                            Box(modifier = Modifier
                                .clip(shape = RoundedCornerShape(15.dp))
                                .background(accent)
                                .padding(top = 3.dp, start = 9.dp)
                            ) {
                                Box(modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .clip(CircleShape)
                                    .size(28.dp)
                                    .background(Color.White)
                                    .clickable {

                                    })
                                {
                                    Icon(
                                        painter = painterResource(R.drawable.favprofile),
                                        contentDescription = "", modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                                Column(modifier = Modifier.padding(top = 34.dp, bottom = 9.dp)
                                    .padding(horizontal = 9.dp)) {

                                    Text(sneaker.small_title)
                                    Text(sneaker.cost.toString())
                                }
                            }
                        }
                    }*/
                }
                Spacer(modifier = Modifier.height(29.dp))
                TextAndAll("Акции", FontWeight.SemiBold, controller){

                }
                Spacer(modifier = Modifier.height(20.dp))
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

            MainScreenViewState.Category -> {
                Box(modifier = Modifier.fillMaxWidth()) {
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
                        /*Text(
                            "Категории", fontSize = 16.sp,
                            fontFamily = textfam,
                            fontWeight = FontWeight.Bold,
                            color = text
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(state.categories) { category ->
                                WhiteButtonMainScreen(
                                    category,
                                    category == vm.state.selectedCategory
                                ) { //Реализоваать переход на кате
                                    vm.updatestate(state.copy(selectedCategory = it))
                                }

                            }

                        }*/
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
                        SneakerItem(sneaker)
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))

            }

            MainScreenViewState.Popular -> {
                //контейнер с кнопкой назад "Популярное" и фав

                TopBar(controller,"Популярное", FontWeight.Medium){ Heart()}

                Spacer(modifier = Modifier.height(30.dp))

                Row() {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        var listSneaker =
                            state.sneakers
                        items(listSneaker) { sneaker ->
                            SneakerItem(sneaker)
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