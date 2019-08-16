package com.jeonguk.web.controller

import com.jeonguk.web.dto.OrderDto
import com.jeonguk.web.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/order")
class OrderController {

    @Autowired
    lateinit var orderService: OrderService

    @PostMapping
    fun insertOrder(@Valid @RequestBody orderDto: OrderDto) {
        orderService.insertOrder(orderDto)
    }
}