package com.example.matuleme2.presentation.screens.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.matuleme2.presentation.screens.components.SpacerHeight
import com.example.matuleme2.presentation.screens.components.TopBar

@Composable
fun OrdersView(controller: NavHostController) {

    val vm = viewModel { OrdersViewModel() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {

        TopBar(controller,"Заказы",FontWeight.SemiBold) { }
        SpacerHeight(28)
    }
}
