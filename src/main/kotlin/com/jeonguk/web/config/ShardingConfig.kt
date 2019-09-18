package com.jeonguk.web.config

import com.jeonguk.web.config.db.DatabaseShardingAlgorithm
import com.jeonguk.web.config.db.TablePreciseShardingAlgorithm
import com.jeonguk.web.config.db.TableRangeShardingAlgorithm
import com.zaxxer.hikari.HikariDataSource
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration
import io.shardingsphere.api.config.rule.TableRuleConfiguration
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import java.util.*
import javax.sql.DataSource

@Configuration
@Profile("!test")
class ShardingConfig {

    private fun orderTableRuleConfiguration(): TableRuleConfiguration {
        return TableRuleConfiguration().apply {
            logicTable = "t_order"
            actualDataNodes = "ds\${0..1}.t_order_\${[0, 1]}"
            keyGeneratorColumnName = "order_id"
        }
    }

    private fun orderItemTableRuleConfiguration(): TableRuleConfiguration {
        return TableRuleConfiguration().apply {
            logicTable = "t_order_item"
            actualDataNodes = "ds\${0..1}.t_order_item_\${[0, 1]}"
        }
    }

    @ConfigurationProperties(prefix = "spring.datasource.ds0.hikari")
    @Bean(name = ["ds0"])
    fun dataSource0(): DataSource {
        return DataSourceBuilder.create()
                .type(HikariDataSource::class.java)
                .build()
    }

    @ConfigurationProperties(prefix = "spring.datasource.ds1.hikari")
    @Bean(name = ["ds1"])
    fun dataSource1(): DataSource {
        return DataSourceBuilder.create()
                .type(HikariDataSource::class.java)
                .build()
    }

    @Primary
    @Bean(name = ["shardingDataSource"])
    fun getDataSource(@Qualifier("ds0") ds0: DataSource, @Qualifier("ds1") ds1: DataSource): DataSource {
        val dataSourceMap = HashMap<String, DataSource>()
        dataSourceMap["ds0"] = ds0
        dataSourceMap["ds1"] = ds1
        val properties = Properties()
        properties.setProperty("sql.show", "true") // SQL Show logging
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, getShardingRuleConfig(), HashMap<String, Any>(), properties)
    }

    private fun getShardingRuleConfig() : ShardingRuleConfiguration {
        return ShardingRuleConfiguration().apply {
            tableRuleConfigs.add(orderTableRuleConfiguration())
            tableRuleConfigs.add(orderItemTableRuleConfiguration())
            bindingTableGroups.add("t_order, t_order_item")
            defaultDatabaseShardingStrategyConfig = StandardShardingStrategyConfiguration("user_id", DatabaseShardingAlgorithm())
            defaultTableShardingStrategyConfig = StandardShardingStrategyConfiguration("order_id", TablePreciseShardingAlgorithm(), TableRangeShardingAlgorithm())
        }
    }

}