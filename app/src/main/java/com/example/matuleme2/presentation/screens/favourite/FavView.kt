package com.example.matuleme2.presentation.screens.favourite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme2.presentation.screens.components.Heart
import com.example.matuleme2.presentation.screens.components.TopBar
import com.example.matuleme2.presentation.screens.components.iconback
import com.example.matuleme2.presentation.screens.main.categories.CategoryViewModel
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam


@Preview
@Composable
fun Preview() {
    FavView(rememberNavController())
}

@Composable
fun FavView(controller: NavHostController) {

    val vm = viewModel { FavViewModel() }
    val state = vm.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        //контейнер с кнопкой назад
        TopBar(controller,"Избранное", FontWeight.SemiBold){ Heart() }

        Spacer(modifier = Modifier.height(35.dp))
    }
}