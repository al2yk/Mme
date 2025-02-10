package com.example.matuleme2.presentation.screens.components

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LocationViewModel : ViewModel() {
    var currentLocation by mutableStateOf<Location?>(null)
}