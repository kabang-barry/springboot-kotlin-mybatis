package com.jeonguk.web.config

import com.jeonguk.web.config.db.DatabaseShardingAlgorithm
import com.jeonguk.web.config.db.TablePreciseShardingAlgorithm
import com.jeonguk.web.config.db.TableRangeShardingAlgorithm
import com.zaxxer.hikari.HikariDataSource
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration
import io.shardingsphere.api.config.rule.TableRuleConfiguration
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import java.util.*
import javax.sql.DataSource

@Configuration
@Profile("test")
class TestShardingConfig {

    private fun orderTableRuleConfiguration(): TableRuleConfiguration {
        val result = TableRuleConfiguration()
        result.logicTable = "t_order"
        result.actualDataNodes = "ds\${0..1}.t_order_\${[0, 1]}"
        result.keyGeneratorColumnName = "order_id"
        return result
    }

    private fun orderItemTableRuleConfiguration(): TableRuleConfiguration {
        val result = TableRuleConfiguration()
        result.logicTable = "t_order_item"
        result.actualDataNodes = "ds\${0..1}.t_order_item_\${[0, 1]}"
        return result
    }

    @Primary
    @Bean(name = ["shardingDataSource"])
    fun getDataSource(): DataSource {
        val dataSourceMap = HashMap<String, DataSource>()
        (0 until 2).forEach {
            val shardName = "ds$it"
            val dataSource = HikariDataSource()
            dataSource.driverClassName = "org.h2.Driver"
            dataSource.jdbcUrl = "jdbc:h2:mem:$shardName;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"
            dataSource.username = "sa"
            dataSource.password = ""
            dataSource.poolName = shardName + "HikariCP"
            dataSource.maximumPoolSize = 200
            dataSource.minimumIdle = 10
            dataSource.connectionTimeout = 30000
            dataSource.connectionTestQuery = "select 1"
            dataSource.maxLifetime = 600000
            dataSource.idleTimeout = 120000

            val initSchema = ClassPathResource("schema-h2.sql")
            //val initData = ClassPathResource("data-h2.sql")
            val databasePopulator = ResourceDatabasePopulator(initSchema)
            DatabasePopulatorUtils.execute(databasePopulator, dataSource)
            dataSourceMap[shardName] = dataSource
        }
        val properties = Properties()
        properties.setProperty("sql.show", "true") // SQL Show logging
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, getShardingRuleConfig(), HashMap<String, Any>(), properties)
    }

    private fun getShardingRuleConfig() : ShardingRuleConfiguration {
        val shardingRuleConfig = ShardingRuleConfiguration()
        shardingRuleConfig.tableRuleConfigs.add(orderTableRuleConfiguration())
        shardingRuleConfig.tableRuleConfigs.add(orderItemTableRuleConfiguration())
        shardingRuleConfig.bindingTableGroups.add("t_order, t_order_item")
        shardingRuleConfig.defaultDatabaseShardingStrategyConfig = StandardShardingStrategyConfiguration("user_id", DatabaseShardingAlgorithm())
        shardingRuleConfig.defaultTableShardingStrategyConfig = StandardShardingStrategyConfiguration("order_id", TablePreciseShardingAlgorithm(), TableRangeShardingAlgorithm())
        return shardingRuleConfig
    }

}