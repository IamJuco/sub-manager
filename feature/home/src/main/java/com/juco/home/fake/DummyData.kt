package com.juco.home.fake

import com.juco.local.model.Subscription

val dummySubscriptions = listOf(
    Subscription(
        subId = 1,
        name = "Netflix",
        thumbnail = "",
        price = 17000,
        paymentDay = 1,
        description = "프리미엄 요금제",
        paymentCycleType = "",
        paymentCycleValue = 1
    ), Subscription(
        subId = 2,
        name = "Youtube Premium",
        thumbnail = "",
        price = 14900,
        paymentDay = 15,
        description = "광고 제거 및 오프라인 저장",
        paymentCycleType = "",
        paymentCycleValue = 1
    ), Subscription(
        subId = 3,
        name = "Spotify",
        thumbnail = "",
        price = 10900,
        paymentDay = 25,
        description = null,
        paymentCycleType = "",
        paymentCycleValue = 1
    ), Subscription(
        subId = 4,
        name = "Coupang Wow",
        thumbnail = "",
        price = 4990,
        paymentDay = 10,
        description = "로켓배송",
        paymentCycleType = "",
        paymentCycleValue = 1
    ), Subscription(
        subId = 5,
        name = "Adobe Creative Cloud",
        thumbnail = "",
        price = 62000,
        paymentDay = 28,
        description = "포토샵, 일러스트레이터 사용",
        paymentCycleType = "",
        paymentCycleValue = 1
    ), Subscription(
        subId = 6,
        name = "Naver Plus",
        thumbnail = "",
        price = 4900,
        paymentDay = 5,
        description = "네이버 페이 적립",
        paymentCycleType = "",
        paymentCycleValue = 1
    ), Subscription(
        subId = 7,
        name = "ChatGPT Plus",
        thumbnail = "",
        price = 29000,
        paymentDay = 21,
        description = "GPT-4 사용",
        paymentCycleType = "",
        paymentCycleValue = 1
    ), Subscription(
        subId = 8,
        name = "Disney Plus",
        thumbnail = "",
        price = 9900,
        paymentDay = 12,
        description = null,
        paymentCycleType = "",
        paymentCycleValue = 1
    )
)