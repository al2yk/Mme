package com.example.matuleme2.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.matuleme2.R
import com.example.matuleme2.presentation.ui.theme.red

@Composable
fun BagWithRed(){
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier
            .align(Alignment.TopEnd)
            .clip(CircleShape)
            .size(44.dp)
            .background(Color.White)
            .clickable {}) {
            Icon(
                painter = painterResource(R.drawable.bag),
                contentDescription = "", modifier = Modifier.align(Alignment.Center)
            )
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .size(44.dp)
                    .padding(end = 7.dp, top = 5.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ellipse), tint = red,
                    contentDescription = "", modifier = Modifier
                        .size(8.dp)
                        .align(Alignment.TopEnd)
                )
            }

        }
    }

}