package com.jeonguk.web

import org.slf4j.LoggerFactory
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

	private val log = LoggerFactory.getLogger(Application::class.java)

	@Autowired
	lateinit var applicationContext : ApplicationContext

	// Bean 확인용
	override fun run(vararg args: String?) {
		val beanNames = applicationContext.beanDefinitionNames
		Arrays.sort(beanNames)
		for (beanName in beanNames) {
			log.info("Bean : {}", beanName)
		}
	}

}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
