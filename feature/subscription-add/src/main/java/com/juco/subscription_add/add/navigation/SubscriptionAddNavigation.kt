package com.juco.subscription_add.add.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.juco.common.constant.Constants
import com.juco.common.model.SubscriptionQuickStartInfo
import com.juco.common.navigation.RouteModel
import com.juco.subscription_add.add.SubscriptionAddRoute

fun NavController.navigateToSubscriptionAdd(navOptions: NavOptions) {
    navigate(
        route = RouteModel.SubscriptionAdd,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.subscriptionAddNavGraph(
    padding: PaddingValues,
    navController: NavController,
    navigateToHome: () -> Unit,
    onShowSnackBar: (String) -> Unit,
    onPopBackStack: () -> Unit

) {
    composable<RouteModel.SubscriptionAdd> { backStackEntry ->
        val quickStartInfo = navController.previousBackStackEntry
            ?.savedStateHandle
            ?.get<SubscriptionQuickStartInfo>(Constants.SUBSCRIPTION_QUICK_START)
        SubscriptionAddRoute(
            padding = padding,
            quickStartInfo = quickStartInfo,
            navigateToHome = navigateToHome,
            onShowSnackBar = onShowSnackBar,
            onPopBackStack = onPopBackStack
        )
    }
}