package com.example.matuleme2.presentation.screens.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.matuleme2.R
import com.example.matuleme2.data.models.Sneaker
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.block
import com.example.matuleme2.presentation.ui.theme.red
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam

//Контейнер с кросовком
@Composable
fun SneakerItem(sneaker: Sneaker, isFavourite: Boolean, onClickFav: () -> Unit, onClick:()->Unit) {
    var height by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current


    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .background(background)
            .padding(top = 3.dp, start = 9.dp)
            .heightIn(min = height)
            .onSizeChanged { height = with(density) { it.height.toDp() } }
            .clickable {
                onClick()
                //controller.navigate(route = "product/${sneaker.id_sneaker}")
            }
    ) {
        //цвет иконки
        var favIconColor: Color = text
        //Какая иконка используется
        var favIconRes = R.drawable.favprofile

        //если данный кросовок - фэйворит - цвет красный иконка соответсвующая
        if (isFavourite) {
            favIconColor = red
            favIconRes = R.drawable.icon_is_fav
        }

        Box(modifier = Modifier.padding(top = 9.dp, start = 9.dp)) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .clip(CircleShape)
                    .size(28.dp)
                    .background(block)
                    .clickable {
                        onClickFav()
                    }
            ) {
                Icon(
                    painter = painterResource(favIconRes), tint = favIconColor,
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(15.dp))
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 34.dp)
                .padding(horizontal = 9.dp)
                .fillMaxHeight(1f)
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

            //Цена
            Row(verticalAlignment = Alignment.Bottom) {
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
    }
}