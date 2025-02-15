package com.example.barcode

import android.content.Context
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.simonsickle.compose.barcodes.BarcodeType

@Composable
fun Barcode(uuidUser: String, viewModel:ViewModelBarcode = viewModel()) {

    val context = LocalContext.current
    //Запомниаем начальное значение
    val brightnessState = remember { mutableStateOf(Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_BRIGHTNESS)) }

    DisposableEffect(Unit) {
        //Запускаем увелечение яркости экрана
        viewModel.changeScreenBrightness(context, Int.MAX_VALUE)
        //Запуститься после закрытия экрана
        onDispose {
            viewModel.changeScreenBrightness(context, brightnessState.value)
        }
    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        com.simonsickle.compose.barcodes.Barcode(
            modifier = Modifier
                .fillMaxSize()
                .rotate(90f).
                padding(2.dp),
            resolutionFactor = 1,
            type = BarcodeType.CODE_128,
            value = uuidUser
        )
    }
}
