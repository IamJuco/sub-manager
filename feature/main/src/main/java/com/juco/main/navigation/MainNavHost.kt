package com.juco.main.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.juco.home.navigation.homeNavGraph
import com.juco.subscription_add.navigation.subscriptionAddNavGraph

@Composable
fun MainNavHost(
    navigator: MainNavigator,
    padding: PaddingValues,
    onShowSnackBar: (String) -> Unit,
    context: Context
) {
    NavHost(
        navController = navigator.navController,
        startDestination = navigator.startDestination
    ) {
        homeNavGraph(
            padding = padding
        )
        subscriptionAddNavGraph(
            padding = padding
        )
    }
}