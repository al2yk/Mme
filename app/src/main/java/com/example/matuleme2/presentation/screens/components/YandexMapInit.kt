package com.example.matuleme2.presentation.screens.components

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.MapKitFactory

class YandexMapInit {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLocation by mutableStateOf<Location?>(null)

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>


    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                currentLocation = location
            }
    }
}
