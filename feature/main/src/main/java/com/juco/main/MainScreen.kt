package com.juco.main

import android.content.Context
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.juco.main.component.BottomNavigationBar
import com.juco.main.navigation.MainNavHost
import com.juco.main.navigation.MainNavigator
import com.juco.main.navigation.rememberMainNavigator
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
    context: Context = LocalContext.current
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val snackBarHostState = remember { SnackbarHostState() }

    val onShowSnackBar: (String) -> Unit = { msg ->
        lifecycleScope.launch { snackBarHostState.showSnackbar(msg) }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        content = { paddingValues ->
            MainNavHost(
                navigator = navigator,
                onShowSnackBar = onShowSnackBar,
                padding = paddingValues,
                context = context
            )
        },
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier.navigationBarsPadding(),
                currentMenu = navigator.currentMenu,
                onMenuSelected = { navigator.navigate(it) }
            )
        }
    )
}

