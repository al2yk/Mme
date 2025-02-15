package com.example.matuleme2.presentation.screens.components

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.example.matuleme2.presentation.getLocationFromAddress
import ru.sulgik.mapkit.compose.YandexMap
import ru.sulgik.mapkit.compose.bindToLifecycleOwner
import ru.sulgik.mapkit.compose.rememberAndInitializeMapKit
import ru.sulgik.mapkit.compose.rememberCameraPositionState
import ru.sulgik.mapkit.geometry.Point
import java.util.Locale


//Функция если нужно написать адрес и показать его на карте
@Composable
fun MapScreen2(address1: String) {
    // Инициализация карты
    rememberAndInitializeMapKit().bindToLifecycleOwner()

    // Состояние для хранения адреса и местоположения
    var address by remember { mutableStateOf(address1) }
    var location by remember { mutableStateOf<Pair<Double, Double>?>(null) }

    // Получение местоположения из адреса
    val context = LocalContext.current
    location = getLocationFromAddress(context = context, address = address)

    // Проверка наличия местоположения
    if (location != null) {
        val startPosition = ru.sulgik.mapkit.map.CameraPosition(
            Point(
                location!!.first,
                location!!.second
            ), // Используем first и second для доступа к координатам
            15.0f,
            0.0f,
            0.0f
        )
        val cameraPositionState = rememberCameraPositionState { position = startPosition }
        YandexMap(
            cameraPositionState = cameraPositionState,
            modifier = Modifier.fillMaxSize()
        )
    } else {
        // Обработка случая, когда местоположение не доступно
        Text("Waiting for location...")
    }
}

@Composable
fun MapScreen(location: Location?) {
    rememberAndInitializeMapKit().bindToLifecycleOwner()

    if (location != null) {
        val startPosition = ru.sulgik.mapkit.map.CameraPosition(
            Point(location.latitude, location.longitude),
            15.0f,
            0.0f,
            0.0f
        )
        val cameraPositionState = rememberCameraPositionState { position = startPosition }
        YandexMap(
            cameraPositionState = cameraPositionState,
            modifier = Modifier.fillMaxSize()
        )
    } else {
        // Обработка случая, когда местоположение не доступно
        Text("Waiting for location...")
    }
}


//получение адреса из геопозиции
fun getAddressFromLocation(context: Context, location: Location?): String? {
    // Проверяем, есть ли разрешение на доступ к местоположению
    if (location != null && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)

        if (addresses != null) {
            return if (addresses.isNotEmpty()) {
                addresses[0].getAddressLine(0) // Возвращаем строку адреса
            } else {
                null // Адрес не найден
            }
        }
    }
    return null // Местоположение недоступно или разрешение не предоставлено
}
/*

@Composable
fun MapScreen(*/
/*location: Location?*//*
 address1: String) {

    rememberAndInitializeMapKit().bindToLifecycleOwner()
    var address by remember { mutableStateOf("") }
    var location by remember { mutableStateOf<Pair<Double, Double>?>(null) }

    location = getLocationFromAddress(context = LocalContext.current, address =  address1)

    if (location != null) {
        val startPosition = ru.sulgik.mapkit.map.CameraPosition(
            Point(location.latitude, location.longitude),
            15.0f,
            0.0f,
            0.0f
        )
        val cameraPositionState = rememberCameraPositionState { position = startPosition }
        YandexMap(
            cameraPositionState = cameraPositionState,
            modifier = Modifier.fillMaxSize()
        )
    } else {
        // Обработка случая, когда местоположение не доступно
        Text("Waiting for location...")
    }
}
*/
