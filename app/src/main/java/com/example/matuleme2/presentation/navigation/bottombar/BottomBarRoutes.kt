package com.example.matuleme2.presentation.navigation.bottombar

import com.example.matuleme2.R
import com.example.matuleme2.presentation.navigation.NavigationRoutes

sealed class BottomBarRoutes(
    val route: String,
    val resourceId: Int? = null
) {

    object MainScreen : BottomBarRoutes(
        route = NavigationRoutes.MAIN,
        resourceId = R.drawable.bag
    )

    object FavScreen : BottomBarRoutes(
        route = NavigationRoutes.FAVOURITE,
        resourceId = R.drawable.bag
    )

    object BuckScreen : BottomBarRoutes(
        route = NavigationRoutes.BUCKET,
        resourceId = R.drawable.bag
    )

    object NotScreen : BottomBarRoutes(
        route = NavigationRoutes.NOTIFICATION,
        resourceId = R.drawable.bag
    )

    object ProfileScreen : BottomBarRoutes(
        route = NavigationRoutes.PROFILE,
        resourceId = R.drawable.bag
    )

}