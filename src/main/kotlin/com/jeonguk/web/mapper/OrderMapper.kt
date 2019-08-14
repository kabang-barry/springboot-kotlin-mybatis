package com.jeonguk.web.mapper

import com.jeonguk.web.domain.Order
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface OrderMapper {
    fun selectByUserId(userId: Int?): List<Order>
    fun selectByOrderIdBetween(@Param("startOrderId") startOrderId: Long?, @Param("endOrderId") endOrderId: Long?): List<Order>
}