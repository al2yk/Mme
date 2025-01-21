package com.example.matuleme2.presentation.screens.main.popular

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.example.matuleme2.data.models.Sneaker
import com.example.matuleme2.presentation.screens.components.iconback
import com.example.matuleme2.presentation.screens.profile.editprofile.EditProfileView
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam
import kotlin.math.sin


@Preview()
@Composable
fun Preview() {
    PopularView(rememberNavController())
}

@Composable
fun PopularView(controller: NavHostController) {

    val vm = viewModel { PopularViewModel() }
    val state = vm.state

    LaunchedEffect(Unit)
    {
        vm.getData()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        //контейнер с кнопкой назад "Популярное" и фав
        Box(modifier = Modifier.fillMaxWidth()) {
            iconback(controller)
            Text(
                "Популярное",
                fontFamily = textfam,
                fontWeight = FontWeight.Medium,
                color = text,
                modifier = Modifier.align(Alignment.Center)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clip(CircleShape)
                    .size(44.dp)
                    .background(Color.White)
                    .clickable {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.favprofile),
                    contentDescription = "",
                    modifier = Modifier.align(Alignment.Center).size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Row() {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(state.Sneakers.filter { it.is_popular }){ Sneaker ->
                    Box(
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
                                    .clickable {}
                            ) {
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
                                    .data(Sneaker.image)
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
                            Text(Sneaker.small_title)
                            Spacer(modifier = Modifier.height(14.dp))
                            Row() {
                                Text("₽${String.format("%.2f", Sneaker.cost)}")
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
                    }

                }
            }

        }
    }
}

