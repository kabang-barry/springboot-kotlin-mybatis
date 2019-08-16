package com.jeonguk.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import java.util.*

@SpringBootApplication(
	exclude = [
		DataSourceAutoConfiguration::class,
		HibernateJpaAutoConfiguration::class,
		DataSourceTransactionManagerAutoConfiguration::class],
	scanBasePackages = ["com.jeonguk.web"]
)
class Application : CommandLineRunner {

	@Autowired
	lateinit var applicationContext : ApplicationContext

	override fun run(vararg args: String?) {
		val beanNames = applicationContext.beanDefinitionNames
		Arrays.sort(beanNames)
		for (beanName in beanNames) {
			println(beanName)
		}
	}

}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
