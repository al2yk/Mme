package com.example.matuleme2.presentation.navigation.bottombar

import com.example.matuleme2.R
import com.example.matuleme2.presentation.navigation.NavigationRoutes

sealed class BottomBarRoutes(
    val route: String,
    val resourceId: Int? = null
) {

    object MainScreen : BottomBarRoutes(
        route = NavigationRoutes.MAIN,
        resourceId = R.drawable.home_2
    )

    object FavScreen : BottomBarRoutes(
        route = NavigationRoutes.FAVOURITE,
        resourceId = R.drawable.favprofile
    )

    object BuckScreen : BottomBarRoutes(
        route = NavigationRoutes.BUCKET,
        resourceId = R.drawable.bag
    )

    object NotScreen : BottomBarRoutes(
        route = NavigationRoutes.NOTIFICATION,
        resourceId = R.drawable.notification
    )

    object ProfileScreen : BottomBarRoutes(
        route = NavigationRoutes.BASICPROFILE,
        resourceId = R.drawable.profile
    )

}