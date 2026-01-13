package com.juco.subscription_edit.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.juco.common.navigation.RouteModel
import com.juco.subscription_edit.SubscriptionEditRoute

fun NavController.navigateToSubscriptionEdit(subId: Long, navOptions: NavOptions) {
    navigate(RouteModel.SubscriptionEdit(subId), navOptions)
}

fun NavGraphBuilder.subscriptionEditNavGraph(
    padding: PaddingValues,
    onPopBackStack: () -> Unit,
    onShowSnackBar: (String) -> Unit
) {
    composable<RouteModel.SubscriptionEdit> { backStackEntry ->
        val subId = backStackEntry.toRoute<RouteModel.SubscriptionEdit>().subId
        SubscriptionEditRoute(
            padding = padding,
            onPopBackStack = onPopBackStack,
            subId = subId,
            onShowSnackBar = onShowSnackBar
        )
    }
}