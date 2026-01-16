package com.juco.main.navigation

import android.content.Context
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.juco.home.navigation.homeNavGraph
import com.juco.setting.navigation.settingNavGraph
import com.juco.subscription_add.add.navigation.subscriptionAddNavGraph
import com.juco.subscription_add.intro.navigation.subscriptionAddIntroNavGraph
import com.juco.subscription_detail.navigation.subscriptionDetailNavGraph
import com.juco.subscription_edit.navigation.subscriptionEditNavGraph

private const val TIME_DURATION = 300

@Composable
fun MainNavHost(
    navigator: MainNavigator,
    padding: PaddingValues,
    onShowSnackBar: (String) -> Unit,
    context: Context,
    appVersion: String,
    isAppUpdateAvailable: Boolean,
    onUpdateClick: () -> Unit
) {
    NavHost(
        navController = navigator.navController,
        startDestination = navigator.startDestination,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(TIME_DURATION)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(TIME_DURATION),
                targetOffset = { fullWidth -> fullWidth / 3 }
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(TIME_DURATION),
                initialOffset = { fullWidth -> fullWidth / 3 }
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(TIME_DURATION)
            )
        }
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
            onShowSnackBar = onShowSnackBar,
            navigateToSubscriptionEdit = navigator::navigateToSubscriptionEdit
        )
        subscriptionEditNavGraph(
            padding = padding,
            onPopBackStack = navigator::popBackStack,
            onShowSnackBar = onShowSnackBar
        )
        settingNavGraph(
            padding = padding,
            appVersion = appVersion,
            isAppUpdateAvailable = isAppUpdateAvailable,
            onUpdateClick = onUpdateClick
        )
    }
}