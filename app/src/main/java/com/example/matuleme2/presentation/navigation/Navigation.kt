package com.example.matuleme2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.matuleme2.presentation.screens.Matuleme.onboarding.OnBoardingScreen
import com.example.matuleme2.presentation.screens.Matuleme.splash.splashscreen
import com.example.matuleme2.presentation.screens.SignUp.SignUpView
import com.example.matuleme2.presentation.screens.main.popular.MainScreen
import com.example.matuleme2.presentation.screens.singin.Signin

@Composable
fun Navigation(controller: NavHostController){

    NavHost(
        startDestination = NavigationRoutes.SPLASH,
        navController = controller
    )
    {
        composable(NavigationRoutes.SIGNIN){
            Signin(controller)
        }
        composable(NavigationRoutes.ONBOARDING){
            OnBoardingScreen(controller)
        }
        composable(NavigationRoutes.SPLASH){
            splashscreen(controller)
        }
        composable(NavigationRoutes.MAIN){
            MainScreen(controller)
        }
        composable(NavigationRoutes.SIGNUP){
            SignUpView(controller)
        }

    }

}