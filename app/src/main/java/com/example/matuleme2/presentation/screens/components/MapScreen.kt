package com.example.matuleme2.presentation.screens.components

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import ru.sulgik.mapkit.compose.YandexMap
import ru.sulgik.mapkit.compose.bindToLifecycleOwner
import ru.sulgik.mapkit.compose.rememberAndInitializeMapKit
import ru.sulgik.mapkit.compose.rememberCameraPositionState
import ru.sulgik.mapkit.geometry.Point


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
