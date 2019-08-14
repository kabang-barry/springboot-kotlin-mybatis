package com.jeonguk.web.domain

data class Order(
        var orderId: Long? = null,
        var userId: Int? = null,
        var status: String? = null
)