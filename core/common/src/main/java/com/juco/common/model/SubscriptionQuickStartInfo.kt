package com.juco.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubscriptionQuickStartInfo(
    val name: String,
    val thumbnail: String,
    val price: Long,
    val description: String
) : Parcelable