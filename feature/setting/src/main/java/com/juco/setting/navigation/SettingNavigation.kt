package com.juco.setting.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.juco.common.navigation.MainRouteModel
import com.juco.setting.SettingRoute

fun NavController.navigateToSetting(navOptions: NavOptions) {
    navigate(MainRouteModel.Setting, navOptions)
}

fun NavGraphBuilder.settingNavGraph(
    padding: PaddingValues,
    appVersion: String,
    isAppUpdateAvailable: Boolean,
    onUpdateClick: () -> Unit
) {
    composable<MainRouteModel.Setting> {
        SettingRoute(
            padding = padding,
            appVersion = appVersion,
            isAppUpdateAvailable = isAppUpdateAvailable,
            onUpdateClick = onUpdateClick
        )
    }
}