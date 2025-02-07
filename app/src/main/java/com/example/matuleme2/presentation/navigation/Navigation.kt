package com.example.matuleme2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.matuleme2.presentation.screens.checkout.CheckOutView
import com.example.matuleme2.presentation.screens.favourite.FavView
import com.example.matuleme2.presentation.screens.forgotpassword.ForgotPasswordView
import com.example.matuleme2.presentation.screens.generatepassword.GenPasswordView
import com.example.matuleme2.presentation.screens.main.MainScreen
import com.example.matuleme2.presentation.screens.notification.NotificationView
import com.example.matuleme2.presentation.screens.onboarding.OnBoardingScreen
import com.example.matuleme2.presentation.screens.otpcheck.OTPCheckView
import com.example.matuleme2.presentation.screens.product.ProductView
import com.example.matuleme2.presentation.screens.profile.basicprofile.BasicProfileView
import com.example.matuleme2.presentation.screens.profile.editprofile.EditProfileView
import com.example.matuleme2.presentation.screens.search.SearchView
import com.example.matuleme2.presentation.screens.sidemenu.SideMenuView
import com.example.matuleme2.presentation.screens.signup.SignUpView
import com.example.matuleme2.presentation.screens.singin.Signin
import com.example.matuleme2.presentation.screens.splash.SplashScreen

@Composable
fun Navigation(controller: NavHostController, visibleBar: MutableState<Boolean>){

    NavHost(
        startDestination = NavigationRoutes.SPLASH,
        navController = controller
    )
    {
        composable(NavigationRoutes.SIGNIN){
            visibleBar.value = false
            Signin(controller)
        }
        composable(NavigationRoutes.ONBOARDING){
            OnBoardingScreen(controller)
        }
        composable(NavigationRoutes.SPLASH){
            SplashScreen(controller)
        }
        composable(NavigationRoutes.MAIN) {
            visibleBar.value = true
            MainScreen(controller)
        }
        composable(NavigationRoutes.SIGNUP){
            visibleBar.value = false
            SignUpView(controller)
        }
        composable(NavigationRoutes.FORGOTPASSWORD) {
            visibleBar.value = false
            ForgotPasswordView(controller)
        }
        composable(NavigationRoutes.OTP + "/{userEmail}") { arg ->
            val userEmail = arg.arguments?.getString("userEmail")
            OTPCheckView(controller, userEmail?:"")
        }
        composable(NavigationRoutes.SIDEMENU){
            visibleBar.value = false
            SideMenuView(controller)
        }
        composable(NavigationRoutes.BASICPROFILE){
            BasicProfileView(controller)
        }
        composable(NavigationRoutes.EDITPROFILE){
            EditProfileView(controller)
        }
        composable(NavigationRoutes.PRODUCT){
            visibleBar.value = false

            ProductView(controller)
        }
        composable(NavigationRoutes.SEARCH){
            SearchView(controller)
            visibleBar.value = false
        }
        composable(NavigationRoutes.NOTIFICATION){
            NotificationView(controller)
        }
        composable(NavigationRoutes.FAVOURITE){
            visibleBar.value = true
            FavView(controller)
        }
        composable(NavigationRoutes.GENERATEPASSWORD) {
            GenPasswordView(controller)
        }
        composable(NavigationRoutes.CHECKOUT){
            CheckOutView(controller)
        }
    }

}