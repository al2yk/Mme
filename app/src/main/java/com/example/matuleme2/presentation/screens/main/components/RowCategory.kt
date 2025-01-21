package com.example.matuleme2.presentation.screens.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matuleme2.data.models.Category
import com.example.matuleme2.data.models.states.MainScreenSearchState
import com.example.matuleme2.presentation.screens.main.mainviewmodel
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam

@Composable
fun RowCategory(
    state: MainScreenSearchState, vm: mainviewmodel, isSelected: Boolean = true,
    onClick: (Category) -> Unit,
) {
    Text(
        "Категории", fontSize = 16.sp,
        fontFamily = textfam,
        fontWeight = FontWeight.Bold,
        color = text
    )
    Spacer(modifier = Modifier.height(20.dp))
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(state.categories) { category ->
            var isSelectedItem = category == vm.state.selectedCategory
            if (!isSelected) isSelectedItem = false
            WhiteButtonMainScreen(
                category,
                isSelectedItem
            ) {onClick(it)}

        }

    }
}