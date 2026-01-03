package com.juco.main.component

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.juco.common.navigation.MainRouteModel
import com.juco.common.navigation.RouteModel
import com.juco.submanager.core.designsystem.R

enum class MainMenu (
    @DrawableRes val iconResIdFalse: Int,
    @DrawableRes val iconResIdTrue: Int,
    val contentDescription: String,
    val route: RouteModel
) {
    HOME(
        iconResIdFalse = R.drawable.ic_home_fill_false,
        iconResIdTrue = R.drawable.ic_home_fill_true,
        contentDescription = "home",
        route = MainRouteModel.Home
    ),
    SETTING(
        iconResIdFalse = R.drawable.ic_setting_fill_false,
        iconResIdTrue = R.drawable.ic_setting_fill_true,
        contentDescription = "setting",
        route = MainRouteModel.Setting
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (RouteModel) -> Boolean): MainMenu? {
            return MainMenu.entries.find { predicate(it.route) }
        }
    }
}
