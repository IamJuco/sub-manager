package com.juco.common.navigation

import kotlinx.serialization.Serializable

sealed interface RouteModel {
    @Serializable
    data object SubscriptionAddIntro : RouteModel

    @Serializable
    data object SubscriptionAdd : RouteModel

    @Serializable
    data class SubscriptionDetail(val subId: Long) : RouteModel

    @Serializable
    data class SubscriptionEdit(val subId: Long) : RouteModel

}

sealed interface MainRouteModel : RouteModel {
    @Serializable
    data object Home : RouteModel

    @Serializable
    data object Setting : RouteModel
}