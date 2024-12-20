package com.example.matuleme2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.matuleme2.presentation.screens.onboarding.OnBoardingScreen
import com.example.matuleme2.presentation.screens.splash.SplashScreen
import com.example.matuleme2.presentation.screens.signup.SignUpView
import com.example.matuleme2.presentation.screens.forgotpassword.ForgotPasswordView
import com.example.matuleme2.presentation.screens.main.popular.MainScreen
import com.example.matuleme2.presentation.screens.otpcheck.OTPCheckView
import com.example.matuleme2.presentation.screens.profile.basicprofile.BasicProfileView
import com.example.matuleme2.presentation.screens.profile.editprofile.EditProfileView
import com.example.matuleme2.presentation.screens.sidemenu.SideMenuView
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
            SplashScreen(controller)
        }
        composable(NavigationRoutes.MAIN){
            MainScreen(controller)
        }
        composable(NavigationRoutes.SIGNUP){
            SignUpView(controller)
        }
        composable(NavigationRoutes.FORGOTPASSWORD){
            ForgotPasswordView(controller)
        }
        composable(NavigationRoutes.OTP){
            OTPCheckView(controller)
        }
        composable(NavigationRoutes.SIDEMENU){
            SideMenuView(controller)
        }
        composable(NavigationRoutes.BASICPROFILE){
            BasicProfileView(controller)
        }
        composable(NavigationRoutes.EDITPROFILE){
            EditProfileView(controller)
        }

    }

}