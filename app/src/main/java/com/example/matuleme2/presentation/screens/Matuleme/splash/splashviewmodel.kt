package com.example.matuleme2.presentation.screens.Matuleme.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme2.domain.repository.UserRepository
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class splashviewmodel : ViewModel(){
    fun launch(controller: NavHostController) {
        viewModelScope.launch {
            delay(3000) //задержка
            if(UserRepository.act == 0) {
                controller.navigate(NavigationRoutes.ONBOARDING) {
                    popUpTo(NavigationRoutes.SPLASH) {
                        inclusive = true
                    }
                }
            }
            if(UserRepository.act == 1) {
                controller.navigate(NavigationRoutes.SIGNIN) {
                    popUpTo(NavigationRoutes.SPLASH) {
                        inclusive = true
                    }
                }
            }
            if(UserRepository.act == 2) {
                controller.navigate(NavigationRoutes.MAIN) {
                    popUpTo(NavigationRoutes.SPLASH) {
                        inclusive = true
                    }
                }
            }
        }
    }
}