package com.example.matuleme2.presentation

import BottomBar
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.matuleme2.domain.repository.UserRepository
import com.example.matuleme2.presentation.navigation.Navigation
import com.example.matuleme2.presentation.ui.theme.MATULEme2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
                    Navigation(controller, isBottomBarVisible)
                }
            }
        }
    }
}
