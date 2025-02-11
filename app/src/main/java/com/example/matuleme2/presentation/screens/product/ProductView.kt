package com.example.matuleme2.presentation.screens.product

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.matuleme2.R
import com.example.matuleme2.presentation.screens.components.BagWithRed
import com.example.matuleme2.presentation.screens.components.MiniIcon
import com.example.matuleme2.presentation.screens.components.SpacerHeight
import com.example.matuleme2.presentation.screens.components.TopBar
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.block
import com.example.matuleme2.presentation.ui.theme.hint
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam

/*
@Preview
@Composable
fun Preview() {
    *//*ProductView(rememberNavController(), index.arguments?.getInt("index"))*//*
}*/

@Composable
fun ProductView(controller: NavHostController, sneakerID: String?) {

    var vm = viewModel { ProductViewModel() }
    var state = vm.state

    LaunchedEffect(Unit) {
        vm.getData()
    }

    var isOpen = remember { mutableStateOf(false) }

    val ListSneakersProduct = state.Sneakers.find { it.id_sneaker == sneakerID }

    if (ListSneakersProduct != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 60.dp)
                    .fillMaxSize()
            ) {


                //header
                TopBar(controller, "Sneaker Shop", FontWeight.Medium) { BagWithRed() }
                Spacer(modifier = Modifier.height(26.dp))
                LazyColumn {
                    item {
                        //Большое название
                        Text(
                            text = ListSneakersProduct.title,
                            fontSize = 26.sp,
                            fontFamily = textfam,
                            fontWeight = FontWeight.Bold,
                            color = text, modifier = Modifier.padding(end = 40.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))


                        Text(
                            ListSneakersProduct.small_title,
                            fontSize = 16.sp,
                            fontFamily = textfam,
                            fontWeight = FontWeight.Medium,
                            color = hint
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        //Цена
                        Text(
                            "₽${String.format("%.2f", ListSneakersProduct.cost)}",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            color = text
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        //Картинка

                        Box(modifier = Modifier.fillMaxWidth()) {
                            val imgState = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(ListSneakersProduct.image)
                                    .size(coil.size.Size.ORIGINAL).build()
                            ).state
                            if (imgState is AsyncImagePainter.State.Error) {
                                CircularProgressIndicator()
                            }
                            if (imgState is AsyncImagePainter.State.Success) {
                                Image(
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .clip(RoundedCornerShape(15.dp))
                                        .padding(40.dp),
                                    painter = imgState.painter,
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Image(
                                painter = painterResource(R.drawable.ten),
                                contentDescription = "",
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                                    .height(130.dp)
                            )


                        }

                        SpacerHeight(28)

                        val listState = rememberLazyListState(Int.MAX_VALUE / 2)
                        Box(modifier = Modifier.fillMaxWidth()) {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                state = listState
                            ) {
                                items(state.Sneakers.size * 10) { index -> // Увеличиваем количество элементов для эффекта бесконечности
                                    val sneakerIndex =
                                        index % state.Sneakers.size // Получаем индекс кроссовка
                                    MiniIcon(
                                        state.Sneakers[sneakerIndex],
                                        onClick = { controller.navigate("product/${state.Sneakers[sneakerIndex].id_sneaker}") }
                                    )
                                }
                            }
                        }
                        SpacerHeight(33)


                        Box(modifier = Modifier.fillMaxWidth()) {
                            //Информация подробная
                            Text(
                                text = ListSneakersProduct.full_description, fontSize = 14.sp,
                                fontFamily = textfam,
                                fontWeight = FontWeight.Normal,
                                color = hint,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterStart)
                                    .padding(end = 40.dp),
                                maxLines = if (isOpen.value) Int.MAX_VALUE else 3,
                                overflow = if (isOpen.value) {
                                    TextOverflow.Visible
                                } else {
                                    TextOverflow.Ellipsis
                                }
                            )
                        }
                        SpacerHeight(9)

                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                if (isOpen.value) "Скрыть" else "Подробно",
                                color = accent,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .clickable {
                                        isOpen.value = !isOpen.value
                                    }
                            )
                        }
                        SpacerHeight(100)
                    }

                }
            }


            //нижняя кнопка
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 20.dp, vertical = 50.dp)
            ) {
                //Нижнее положение иконка и кнопка
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(52.dp)
                            .background(Color.White)
                            .clickable {

                            }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.favprofile),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(18.dp))



                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(accent)
                        .clickable { }) {
                        Icon(
                            painter = painterResource(R.drawable.bag),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 12.dp),
                            tint = Color.White
                        )
                        Text(
                            "В Корзину", fontSize = 14.sp,
                            fontFamily = textfam,
                            fontWeight = FontWeight.SemiBold,
                            color = block, modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    } else {

    }
}
