package com.juco.common.navigation

import kotlinx.serialization.Serializable

sealed interface RouteModel {

}

sealed interface MainRouteModel : RouteModel {
    @Serializable
    data object Home : RouteModel

    @Serializable
    data object Setting : RouteModel
}