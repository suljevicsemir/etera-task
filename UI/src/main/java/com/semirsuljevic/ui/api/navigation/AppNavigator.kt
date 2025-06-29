package com.semirsuljevic.ui.api.navigation

import androidx.navigation.NavHostController

interface AppNavigator {
    fun setNavController(navController: NavHostController)
    fun navigate(route: AppRoute)
    fun navigate(path: String)
    fun pop()

    fun setHome(path: String)
    fun navigateHome()

    fun navigateAndRemove(route: AppRoute)

    fun popUntil(route: AppRoute, inclusive: Boolean = true)
}