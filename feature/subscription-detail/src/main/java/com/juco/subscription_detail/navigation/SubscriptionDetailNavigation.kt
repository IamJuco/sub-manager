package com.juco.subscription_detail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.juco.common.navigation.RouteModel
import com.juco.subscription_detail.SubscriptionDetailRoute

fun NavController.navigateToSubscriptionDetail(subId: Long, navOptions: NavOptions) {
    navigate(RouteModel.SubscriptionDetail(subId), navOptions)
}

fun NavGraphBuilder.subscriptionDetailNavGraph(
    padding: PaddingValues,
    onPopBackStack: () -> Unit,
    onShowSnackBar: (String) -> Unit,
    navigateToSubscriptionEdit: (Long) -> Unit
) {
    composable<RouteModel.SubscriptionDetail> { backStackEntry ->
        val subId = backStackEntry.toRoute<RouteModel.SubscriptionDetail>().subId
        SubscriptionDetailRoute(
            padding = padding,
            onPopBackStack = onPopBackStack,
            onShowSnackBar = onShowSnackBar,
            subId = subId,
            navigateToSubscriptionEdit = navigateToSubscriptionEdit
        )
    }
}