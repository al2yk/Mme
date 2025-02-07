package com.example.matuleme2.presentation.screens.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.matuleme2.presentation.screens.components.BagWithRed
import com.example.matuleme2.presentation.screens.components.Heart
import com.example.matuleme2.presentation.screens.components.TopBar
import com.example.matuleme2.presentation.screens.main.MainScreen
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.block
import com.example.matuleme2.presentation.ui.theme.hint
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam

@Preview
@Composable
fun Preview() {
    ProductView(rememberNavController())
}

@Composable
fun ProductView(controller: NavHostController) {

    var vm = viewModel{ProductViewModel()}
    var state  =vm.state

    LaunchedEffect(Unit) {
        vm.getData()
    }

    val ListSneakersProduct = state.Sneakers
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 60.dp)
            .fillMaxSize()
    ) {
        TopBar(controller, "Sneaker Shop", FontWeight.Medium) { BagWithRed() }
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            "",
            fontSize = 26.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.Bold,
            color = text
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "f",
            fontSize = 16.sp,
            fontFamily = textfam,
            fontWeight = FontWeight.Medium,
            color = hint
        )
        Spacer(modifier = Modifier.height(8.dp))
        /*Text("₽${String.format("%.2f", sneaker.cost)}")*/

        Spacer(modifier = Modifier.height(14.dp))
        /*
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
        }*/

        Spacer(modifier = Modifier.weight(1f))

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(52.dp)
                    .background(Color.White)
                    .clickable {}
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
                Text("В Корзину",fontSize = 14.sp,
                    fontFamily = textfam,
                    fontWeight = FontWeight.SemiBold,
                    color = block, modifier = Modifier.align(Alignment.Center))
            }
        }

    }
}

