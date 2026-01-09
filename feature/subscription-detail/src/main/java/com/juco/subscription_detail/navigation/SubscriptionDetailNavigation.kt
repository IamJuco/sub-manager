package com.juco.subscription_detail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.juco.common.navigation.RouteModel
import com.juco.subscription_detail.SubscriptionDetailRoute

fun NavController.navigateToSubscriptionDetail(navOptions: NavOptions) {
    navigate(RouteModel.SubscriptionDetail, navOptions)
}

fun NavGraphBuilder.subscriptionDetailNavGraph(
    padding: PaddingValues,
    onPopBackStack: () -> Unit
) {
    composable<RouteModel.SubscriptionDetail> {
        SubscriptionDetailRoute(
            padding = padding,
            onPopBackStack = onPopBackStack
        )
    }
}