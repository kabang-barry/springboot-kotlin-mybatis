package com.jeonguk.web.mapper

import com.jeonguk.web.domain.Order
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface OrderMapper {
    fun selectOrderList(): List<Order>
    fun selectByUserId(userId: Int?): List<Order>
    fun selectByOrderIdBetween(@Param("startOrderId") startOrderId: Long?, @Param("endOrderId") endOrderId: Long?): List<Order>
    fun insertOrder(order: Order)
}