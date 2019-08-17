package com.jeonguk.web.service

import com.jeonguk.web.domain.dto.OrderDto
import com.jeonguk.web.domain.model.Order
import com.jeonguk.web.mapper.OrderMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService {

    @Autowired
    lateinit var orderMapper: OrderMapper

    fun selectByUserId(userId: Int): List<OrderDto> {
        return orderMapper.selectByUserId(userId)
    }

    @Transactional
    fun insertOrder(orderDto: OrderDto) {
        val order = Order(userId = orderDto.userId, status = orderDto.status)
        orderMapper.insertOrder(order)
    }

}