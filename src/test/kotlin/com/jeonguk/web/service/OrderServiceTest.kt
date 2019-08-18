package com.jeonguk.web.service

import com.jeonguk.web.domain.model.Order
import com.jeonguk.web.mapper.OrderMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@RunWith(SpringRunner::class)
@ActiveProfiles("test")
@Transactional
class OrderServiceTest {

    private val log = LoggerFactory.getLogger(OrderServiceTest::class.java)

    @Autowired
    lateinit var orderMapper: OrderMapper

    @Test
    fun getUserOrderTest() {
        val order = Order(userId = 11111, status = "ORDERD")
        orderMapper.insertOrder(order)
        val orderList = orderMapper.selectByUserId(11111)
        orderList.forEach { ord -> log.info("order {}", ord) }
    }

}