package com.example.matuleme2.presentation.screens.components.barcode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.barcode.Barcode
import com.example.matuleme2.presentation.screens.components.TopBar
import com.example.matuleme2.presentation.screens.components.iconback

@Composable
fun BarCodeView(id: String,controller:NavHostController) {
    Column(modifier = Modifier.padding(vertical = 60.dp, horizontal = 20.dp)) {
        iconback(controller)
        Barcode(id)
    }
}


