package com.juco.main.navigation

import android.content.Context
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.juco.home.navigation.homeNavGraph
import com.juco.subscription_add.add.navigation.subscriptionAddNavGraph
import com.juco.subscription_add.intro.navigation.subscriptionAddIntroNavGraph
import com.juco.subscription_detail.navigation.subscriptionDetailNavGraph

@Composable
fun MainNavHost(
    navigator: MainNavigator,
    padding: PaddingValues,
    onShowSnackBar: (String) -> Unit,
    context: Context
) {
    NavHost(
        navController = navigator.navController,
        startDestination = navigator.startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        homeNavGraph(
            padding = padding,
            navigateToSubscriptionAddIntro = navigator::navigateToSubscriptionAddIntro,
            navigateToSubscriptionDetail = navigator::navigateToSubscriptionDetail
        )
        subscriptionAddIntroNavGraph(
            padding = padding,
            navigateToSubscriptionAdd = navigator::navigateToSubscriptionAdd,
            onPopBackStack = navigator::popBackStack
        )
        subscriptionAddNavGraph(
            padding = padding,
            navigateToHome = navigator::navigateToHome,
            onShowSnackBar = onShowSnackBar,
            onPopBackStack = navigator::popBackStack,
            navController = navigator.navController
        )
        subscriptionDetailNavGraph(
            padding = padding,
            onPopBackStack = navigator::popBackStack,
            onShowSnackBar = onShowSnackBar
        )
    }
}