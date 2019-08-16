package com.jeonguk.web.config

import org.mybatis.spring.SqlSessionTemplate
import org.apache.ibatis.session.SqlSessionFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = ["com.jeonguk.web.mapper"], sqlSessionTemplateRef = "shardSqlSessionTemplate")
class MybatisConfig {

    @Bean(name = ["shardSqlSessionFactory"])
    fun shardSqlSessionFactory(@Qualifier("shardingDataSource") shardingDataSource: DataSource): SqlSessionFactory? {
        val bean = SqlSessionFactoryBean()
        bean.setDataSource(shardingDataSource)
        bean.setTypeAliasesPackage("com.jeonguk.web.domain")
        bean.setMapperLocations(*PathMatchingResourcePatternResolver().getResources("classpath:mybatis/shardingMapper/*.xml"))
        bean.setConfigLocation(DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"))
        return bean.getObject()
    }

    @Bean(name = ["shardTransactionManager"])
    fun shardTransactionManager(@Qualifier("shardingDataSource") dataSource: DataSource): DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

    @Bean(name = ["shardSqlSessionTemplate"])
    fun shardSqlSessionTemplate(@Qualifier("shardSqlSessionFactory") sqlSessionFactory: SqlSessionFactory): SqlSessionTemplate {
        return SqlSessionTemplate(sqlSessionFactory)
    }

}