package com.jeonguk.web.domain

import java.math.BigInteger

data class Order(
        var orderId: BigInteger? = null,
        var userId: Int? = null,
        var status: String? = null
)