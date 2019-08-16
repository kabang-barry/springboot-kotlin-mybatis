package com.jeonguk.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
	exclude = [
		DataSourceAutoConfiguration::class,
		HibernateJpaAutoConfiguration::class,
		DataSourceTransactionManagerAutoConfiguration::class],
	scanBasePackages = ["com.jeonguk.web"]
)
class SpringbootKotlinMybatisApplication

fun main(args: Array<String>) {
	runApplication<SpringbootKotlinMybatisApplication>(*args)
}
