package com.example.matuleme2.presentation.screens.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.matuleme2.data.models.Notification
import com.example.matuleme2.presentation.screens.components.Banner
import kotlinx.coroutines.delay

@Composable
fun NotificationView(controller: NavHostController) {

    val vm = viewModel { (NotificationViewModel()) }
    val state = vm.state

    LaunchedEffect(Unit) {
        delay(1)
        vm.getNotification()
    }

    var listNotification = state.notificationList


    //Очень хочется прокрутки но не делается(

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(listNotification) { noti ->
                Banner(noti)
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
    }

}
