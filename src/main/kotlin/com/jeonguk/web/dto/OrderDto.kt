package com.jeonguk.web.dto

data class OrderDto(
    var orderId: Long? = null,
    var userId: Int? = null,
    var status: String? = null
)