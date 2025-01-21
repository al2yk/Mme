package com.example.matuleme2.presentation.screens.main.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme2.R
import com.example.matuleme2.presentation.screens.components.iconback
import com.example.matuleme2.presentation.screens.main.MainScreen
import com.example.matuleme2.presentation.screens.main.components.WhiteButtonMainScreen
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam


@Preview
@Composable
fun Preview() {
    CategoryView(rememberNavController())
}
@Composable
fun CategoryView(controller: NavHostController) {

    val vm = viewModel { CategoryViewModel() }
    val state = vm.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        //контейнер с кнопкой назад
        Box(modifier = Modifier.fillMaxWidth()) {
            iconback(controller)
            Text(
                "",
                fontFamily = textfam,
                fontWeight = FontWeight.Medium,
                color = text,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(35.dp))
        Column {
            Text(
                "Категории", fontSize = 16.sp,
                fontFamily = textfam,
                fontWeight = FontWeight.Bold,
                color = text
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                items(state.categories) { category ->
                   /* WhiteButtonMainScreen(category.category) {//Реализоваать переход на категории}
                    }*/

                }

            }
        }

    }
}
