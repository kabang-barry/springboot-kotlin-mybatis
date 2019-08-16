package com.jeonguk.web.service

import com.jeonguk.web.dto.OrderDto
import com.jeonguk.web.mapper.OrderMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService {

    @Autowired
    lateinit var orderMapper: OrderMapper

    fun insertOrder(orderDto: OrderDto) {
        orderMapper.insertOrder(orderDto)
    }
}