package com.jeonguk.web.domain.dto

import java.math.BigInteger

data class OrderDto(
        var orderId: BigInteger? = null,
        var userId: Int? = null,
        var status: String? = null
)