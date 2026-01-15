package com.juco.main

import android.content.Context
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.main.component.BottomNavigationBar
import com.juco.main.navigation.MainNavHost
import com.juco.main.navigation.MainNavigator
import com.juco.main.navigation.rememberMainNavigator
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
    context: Context = LocalContext.current,
    appVersion: String,
    isAppUpdateAvailable: Boolean,
    onUpdateClick: () -> Unit
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val snackBarHostState = remember { SnackbarHostState() }

    var snackBarJob by remember { mutableStateOf<Job?>(null) }
    val onShowSnackBar: (String) -> Unit = { msg ->
        snackBarJob?.cancel()
        snackBarJob = lifecycleScope.launch {
            snackBarHostState.showSnackbar(msg)
        }
    }

    Scaffold(
        containerColor = SubManagerTheme.colors.primaryBackground,
        snackbarHost = { SnackbarHost(snackBarHostState) },
        content = { paddingValues ->
            MainNavHost(
                navigator = navigator,
                onShowSnackBar = onShowSnackBar,
                padding = paddingValues,
                context = context,
                appVersion = appVersion,
                isAppUpdateAvailable = isAppUpdateAvailable,
                onUpdateClick = onUpdateClick
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentMenu = navigator.currentMenu,
                onMenuSelected = { navigator.navigate(it) },
                isVisible = navigator.showBottomBar()
            )
        }

    )
}
