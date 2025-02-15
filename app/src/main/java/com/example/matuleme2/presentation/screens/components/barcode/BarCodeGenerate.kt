package com.example.matuleme2.presentation.screens.components.barcode

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.simonsickle.compose.barcodes.BarcodeType

@Composable
fun BarCodeGenerate(uuidUser: String) {
    if (BarcodeType.CODE_128.isValueValid(uuidUser)) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            com.simonsickle.compose.barcodes.Barcode(
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp),
                resolutionFactor = 1,
                type = BarcodeType.CODE_128,
                value = uuidUser
            )
        }
    }
}