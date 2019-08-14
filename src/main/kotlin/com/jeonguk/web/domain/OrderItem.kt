package com.jeonguk.web.domain

data class OrderItem(
        var orderItemId: Long? = null,
        var orderId: Long? = null,
        var userId: Int? = null
)