package com.example.matuleme2.presentation

import BottomBar
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat.getCurrentLocation
import androidx.navigation.compose.rememberNavController
import com.example.matuleme2.domain.repository.UserRepository
import com.example.matuleme2.presentation.navigation.Navigation
import com.example.matuleme2.presentation.screens.components.LocationViewModel
import com.example.matuleme2.presentation.ui.theme.MATULEme2Theme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

/*    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var currentLocation by mutableStateOf<Location?>(null)

    private lateinit var currentLocationViewModel: LocationViewModel

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>*/

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLocation by mutableStateOf<Location?>(null)

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Инициализация разрешений
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                getCurrentLocation()
            }
        }

        // Запрос разрешений на геолокацию
        when {
            ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                getCurrentLocation()
            }
            else -> {
                requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
            }
        }

        // Инициализация MapKit
        MapKitFactory.setApiKey("25fac3a0-fc49-4545-92b1-d1d64d54dac5")
        MapKitFactory.initialize(this)


        setContent {
            val isBottomBarVisible = remember { mutableStateOf(false) }
            MATULEme2Theme {
                val context = LocalContext.current
                UserRepository.init(context)
                val controller = rememberNavController()
                Scaffold(bottomBar = {
                    if(isBottomBarVisible.value) BottomBar(controller)
                })
                {
                    Navigation(controller, isBottomBarVisible,currentLocation)
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                currentLocation = location
                val geocoder = Geocoder(this, Locale.getDefault())
                try {
                    val addresses =
                        geocoder.getFromLocation(location!!.latitude, location.longitude, 1)
                    if (addresses!!.isNotEmpty()) {
                        Log.d("address", addresses[0].getAddressLine(0))// Возвращает полный адрес
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
    }
}
