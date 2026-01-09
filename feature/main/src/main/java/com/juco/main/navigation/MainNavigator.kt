package com.juco.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.juco.common.constant.Constants
import com.juco.common.model.SubscriptionQuickStartInfo
import com.juco.common.navigation.MainRouteModel
import com.juco.common.navigation.RouteModel
import com.juco.home.navigation.navigateToHome
import com.juco.main.component.MainMenu
import com.juco.subscription_add.add.navigation.navigateToSubscriptionAdd
import com.juco.subscription_add.intro.navigation.navigateToSubscriptionAddIntro

class MainNavigator(
    val navController: NavHostController
) {
    val startDestination = MainRouteModel.Home
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination
    val currentMenu: MainMenu?
        @Composable get() = MainMenu.find { menu ->
            currentDestination?.hasRoute(menu::class) == true
        }

    private val singleTopOptions = navOptions {
        launchSingleTop = true
        restoreState = true
    }

    fun navigate(menu: MainMenu) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = menu == MainMenu.HOME
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (menu) {
            MainMenu.HOME -> navController.navigateToHome(navOptions)
            MainMenu.SETTING -> {}
        }
    }

    fun navigateToHome() = navController.navigateToHome(navOptions = singleTopOptions)
    fun navigateToSubscriptionAddIntro() = navController.navigateToSubscriptionAddIntro(navOptions = singleTopOptions)
    fun navigateToSubscriptionAdd(info: SubscriptionQuickStartInfo? = null) {
        val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle

        if (info != null) {
            savedStateHandle?.set(Constants.SUBSCRIPTION_QUICK_START, info)
        } else {
            savedStateHandle?.remove<SubscriptionQuickStartInfo>(Constants.SUBSCRIPTION_QUICK_START)
        }

        navController.navigateToSubscriptionAdd(navOptions = singleTopOptions)
    }

    fun popBackStack() = navController.popBackStack()
    fun popAllBackStack(destination: RouteModel) =
        navController.popBackStack(destination, false)

    @Composable
    fun showBottomBar(): Boolean {
        val isCurrentMenu = MainMenu.contains {
            currentDestination?.hasRoute(it::class) == true
        }
        return isCurrentMenu
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}