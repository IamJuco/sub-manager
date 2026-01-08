package com.juco.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriptions")
data class SubscriptionEntity(
    @PrimaryKey(autoGenerate = true) val subId: Long = 0,
    val name: String, // 서비스 이름
    val thumbnail: String, // 서비스 썸네일
    val price: Long, // 결제 금액
    val paymentDay: Long, // 결제 일 (사용자가 설정한 결제일)
    val paymentCycleType: String, // 결제 주기 (DAY, MONTH, YEAR, NONE)
    val paymentCycleValue: Int, // 결제 주기 값 (설정한 결제 주기 숫자)
    val description: String? = null // 서비스 설명
)