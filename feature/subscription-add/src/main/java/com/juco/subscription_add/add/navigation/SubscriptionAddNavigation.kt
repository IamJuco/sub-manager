package com.juco.subscription_add.add.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.juco.common.navigation.RouteModel
import com.juco.subscription_add.add.SubscriptionAddRoute

fun NavController.navigateToSubscriptionAdd(navOptions: NavOptions) {
    navigate(RouteModel.SubscriptionAdd, navOptions)
}

fun NavGraphBuilder.subscriptionAddNavGraph(
    padding: PaddingValues,
    navigateToHome: () -> Unit,
    onShowSnackBar: (String) -> Unit,
    onPopBackStack: () -> Unit

) {
    composable<RouteModel.SubscriptionAdd> {
        SubscriptionAddRoute(
            padding = padding,
            navigateToHome = navigateToHome,
            onShowSnackBar = onShowSnackBar,
            onPopBackStack = onPopBackStack
        )
    }
}