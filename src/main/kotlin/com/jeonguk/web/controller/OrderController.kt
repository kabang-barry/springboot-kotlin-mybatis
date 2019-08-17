package com.jeonguk.web.controller

import com.jeonguk.web.domain.dto.OrderDto
import com.jeonguk.web.service.OrderService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/order")
class OrderController {

    private val log = LoggerFactory.getLogger(OrderController::class.java)

    @Autowired
    lateinit var orderService: OrderService

    @GetMapping("/{userId}")
    fun getOrderByUserId(@PathVariable("userId") userId: Int) : List<OrderDto> {
        return orderService.selectByUserId(userId)
    }

    @PostMapping
    fun insertOrder(@Valid @RequestBody orderDto: OrderDto) {
        orderService.insertOrder(orderDto)
    }

}