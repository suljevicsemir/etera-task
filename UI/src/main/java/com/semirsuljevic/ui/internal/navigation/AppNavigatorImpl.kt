package com.semirsuljevic.ui.internal.navigation



import android.content.res.Resources.NotFoundException
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.semirsuljevic.ui.api.navigation.AppNavigator
import com.semirsuljevic.ui.api.navigation.AppRoute
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigatorImpl @Inject constructor(): AppNavigator {
    private lateinit var navigator: NavHostController
    private var homePath: String = ""
    override fun setNavController(navController: NavHostController) {
        navigator = navController
    }

    override fun navigate(route: AppRoute) {
        navigator.navigate(route.path)
    }

    override fun navigate(path: String) {
        navigator.navigate(path)
    }

    override fun pop() {
        navigator.navigateUp()
    }

    override fun setHome(path: String) {
        homePath = path
    }

    override fun navigateHome() {
        if(homePath.isEmpty()) {
            throw NotFoundException("Home path not set, call setHome() before using navigateHome()")
        }
        navigator.navigate(homePath) {
            popUpTo(navigator.graph.findStartDestination().id) {
                inclusive = true
            }
        }
    }

    override fun navigateAndRemove(route: AppRoute) {
        navigator.navigate(route.path) {
            popUpTo(navigator.graph.findStartDestination().id) {
                inclusive = true
            }
        }
    }

    override fun popUntil(route: AppRoute, inclusive: Boolean) {
        navigator.popBackStack(route.path, inclusive)
    }
}
