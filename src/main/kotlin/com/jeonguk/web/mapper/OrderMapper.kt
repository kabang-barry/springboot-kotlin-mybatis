package com.jeonguk.web.mapper

import com.jeonguk.web.domain.model.Order
import com.jeonguk.web.domain.dto.OrderDto
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface OrderMapper {
    fun selectByUserId(userId: Int?): List<OrderDto>
//    fun selectByOrderIdBetween(@Param("startOrderId") startOrderId: Long?, @Param("endOrderId") endOrderId: Long?): List<Order>
    fun insertOrder(order: Order)
}