package com.juco.common.navigation

import kotlinx.serialization.Serializable

sealed interface RouteModel {
    @Serializable
    data object SubscriptionAddIntro : RouteModel

    @Serializable
    data object SubscriptionAdd : RouteModel

    @Serializable
    data object SubscriptionDetail : RouteModel

}

sealed interface MainRouteModel : RouteModel {
    @Serializable
    data object Home : RouteModel

    @Serializable
    data object Setting : RouteModel
}