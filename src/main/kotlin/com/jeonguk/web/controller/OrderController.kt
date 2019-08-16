package com.jeonguk.web.controller

import com.jeonguk.web.domain.Order
import com.jeonguk.web.dto.OrderDto
import com.jeonguk.web.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/order")
class OrderController {

    @Autowired
    lateinit var orderService: OrderService

    @GetMapping("/{userId}")
    fun getOrderByUserId(@PathVariable("userId") userId: Int) : List<Order> {
        return orderService.selectByUserId(userId)
    }

    @PostMapping
    fun insertOrder(@Valid @RequestBody orderDto: OrderDto) {
        orderService.insertOrder(orderDto)
    }
}