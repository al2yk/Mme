package com.example.matuleme2.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme2.domain.repository.UserRepository
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    fun launch(controller: NavHostController) {
        viewModelScope.launch {
            delay(3000) //задержка
            if(UserRepository.act == Acts.ONBOARDING) {
                controller.navigate(NavigationRoutes.ONBOARDING) {
                    popUpTo(NavigationRoutes.SPLASH) {
                        inclusive = true
                    }
                }
            }
            if(UserRepository.act == Acts.SIGNIN) {
                controller.navigate(NavigationRoutes.SIGNIN) {
                    popUpTo(NavigationRoutes.SPLASH) {
                        inclusive = true
                    }
                }
            }
            if(UserRepository.act == Acts.MAIN) {
                controller.navigate(NavigationRoutes.MAIN) {
                    popUpTo(NavigationRoutes.SPLASH) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

