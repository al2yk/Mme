package com.example.matuleme2.presentation.screens.favourite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme2.presentation.screens.components.Heart
import com.example.matuleme2.presentation.screens.components.TopBar
import com.example.matuleme2.presentation.screens.main.components.SneakerItem


@Preview
@Composable
fun Preview() {
    FavView(rememberNavController())
}

@Composable
fun FavView(controller: NavHostController) {

    val vm = viewModel { FavViewModel() }
    val state = vm.state

    LaunchedEffect(Unit) {vm.getData()}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        //контейнер с кнопкой назад
        TopBar(controller,"Избранное", FontWeight.SemiBold){ Heart(){} }
        Spacer(modifier = Modifier.height(35.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            //лист фав кросовок
            val listSneaker =
                state.sneakers.filter { state.idFavSneakers.contains(it.id_sneaker) }
            // фильтр, те которые в базе фав

            items(listSneaker) { sneaker ->
                SneakerItem(sneaker, state.idFavSneakers.contains(sneaker.id_sneaker)) {
                    vm.clickFavIcon(sneaker)
                }
            }
        }
    }
}