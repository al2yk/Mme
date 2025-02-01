@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.matuleme2.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.matuleme2.presentation.screens.components.TopBar
import com.example.matuleme2.presentation.screens.main.components.SneakerItem
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.hint
import com.example.matuleme2.presentation.ui.theme.subtextdark
import com.example.matuleme2.presentation.ui.theme.textfam


@Preview
@Composable
fun Preview() {
    SearchView(rememberNavController())
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchView(controller: NavHostController) {


    val vm = viewModel { SearchViewModel() }
    val state = vm.state

    var textS by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var onClickSearch by remember { mutableStateOf(false) }


    var clickSearch = true
    //история. как убрать "" и чтобы сохронялась в кэше?
    val items = remember { mutableStateListOf("") }
    val sn by vm.sneakersvm.collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 48.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth().padding(vertical = 0.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            SearchBar(
                modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter).background(background)
                    .padding(vertical = 0.dp),
                query = textS,
                onQueryChange = {
                    textS = it
                },
                onSearch = {
                    clickSearch = false
                    items.add(textS)
                    active = false
                    onClickSearch = true
                    vm.searchSneaker(textS)

                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = {
                    Text(
                        "Поиск",
                        color = hint,
                        fontSize = 16.sp,
                        fontFamily = textfam,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }, leadingIcon = {
                    Icon(
                        painter =
                        painterResource(R.drawable.searchicon),
                        contentDescription = "",
                        tint = hint,
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }, trailingIcon = {
                    Icon(
                        painter =
                        painterResource(R.drawable.mic),
                        contentDescription = "",
                        tint = subtextdark,
                        modifier = Modifier.padding(end = 15.dp)
                    )

                }, colors = SearchBarDefaults.colors(
                    containerColor = Color.Transparent,
                    dividerColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(15.dp)

            ) {
                Spacer(modifier = Modifier.height(8.dp))
                if (clickSearch) {
                    items.forEach {
                        if (it != "") {
                            Row(modifier = Modifier.padding(top = 16.dp)) {
                                Icon(
                                    modifier = Modifier
                                        .padding(end = 10.dp)
                                        .align(Alignment.CenterVertically),
                                    painter = painterResource(R.drawable.clock),
                                    contentDescription = "", tint = subtextdark
                                )
                                Text(text = it, modifier = Modifier.align(Alignment.CenterVertically))
                            }
                        }
                    }

                }
            }
            //ЗАДНИЙ ФОН ЗА КРАСОВКАМИ
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 154.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                /*Spacer(modifier = Modifier.height(10.dp))*/
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    //Очень хочется, но не работает, lazycolumn не помогает
                    //modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    items(state.sneakerslist) { sneaker ->
                        SneakerItem(sneaker, false, {})
                    }
                }
            }

        }
        TopBar(controller, "Поиск", FontWeight.SemiBold) { }
    }

}
