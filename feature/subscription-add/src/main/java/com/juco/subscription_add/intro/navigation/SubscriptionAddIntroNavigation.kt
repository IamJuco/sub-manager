package com.juco.subscription_add.intro.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.juco.common.navigation.RouteModel
import com.juco.subscription_add.intro.SubscriptionAddIntroRoute

fun NavController.navigateToSubscriptionAddIntro(navOptions: NavOptions) {
    navigate(RouteModel.SubscriptionAddIntro, navOptions)
}

fun NavGraphBuilder.subscriptionAddIntroNavGraph(
    padding: PaddingValues,
    navigateToSubscriptionAdd: () -> Unit,
    onPopBackStack: () -> Unit
) {
    composable<RouteModel.SubscriptionAddIntro> {
        SubscriptionAddIntroRoute(
            padding = padding,
            navigateToSubscriptionAdd = navigateToSubscriptionAdd,
            onPopBackStack = onPopBackStack
        )
    }
}