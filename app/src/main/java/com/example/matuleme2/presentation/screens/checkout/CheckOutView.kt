package com.example.matuleme2.presentation.screens.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.matuleme2.presentation.screens.components.TopBar

@Composable
fun CheckOutView(controller: NavHostController) {

    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(controller,"Корзина", FontWeight.SemiBold){}
        Spacer(modifier = Modifier.height(58.dp))
    }
}
