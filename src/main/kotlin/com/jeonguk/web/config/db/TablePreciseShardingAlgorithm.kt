package com.jeonguk.web.config.db

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm

class TablePreciseShardingAlgorithm : PreciseShardingAlgorithm<Long> {

    override fun doSharding(availableTargetNames: Collection<String>, shardingValue: PreciseShardingValue<Long>): String {
        val size = availableTargetNames.size
        for (each in availableTargetNames) {
            if (each.endsWith((shardingValue.value % size).toString() + "")) {
                return each
            }
        }
        throw UnsupportedOperationException()
    }

}