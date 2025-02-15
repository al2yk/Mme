package com.example.matuleme2.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.matuleme2.data.models.Sneaker
import com.example.matuleme2.presentation.ui.theme.block

@Composable
fun MiniIcon(sneaker: Sneaker,sizeicon:Int,onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .size(sizeicon.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(block)
            .clickable { onClick() }
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
                    .clip(RoundedCornerShape(15.dp))
                    .align(Alignment.Center),
                painter = imgState.painter,
                contentDescription = "",
                contentScale = ContentScale.Fit
            )
        }

    }
}