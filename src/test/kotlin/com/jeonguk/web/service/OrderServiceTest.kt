package com.jeonguk.web.service

import com.jeonguk.web.mapper.OrderMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@RunWith(SpringRunner::class)
@Transactional
class OrderServiceTest {

    private val log = LoggerFactory.getLogger(OrderServiceTest::class.java)

    @Autowired
    lateinit var orderMapper: OrderMapper

    @Test
    fun getOrderTest() {
        val orderList = orderMapper.selectOrderList()
        orderList.forEach { order -> log.info("order {}", order) }
    }
}