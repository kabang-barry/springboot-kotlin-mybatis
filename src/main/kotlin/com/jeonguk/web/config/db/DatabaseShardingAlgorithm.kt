package com.jeonguk.web.config.db

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm

class DatabaseShardingAlgorithm : PreciseShardingAlgorithm<Int> {

    override fun doSharding(availableTargetNames: Collection<String>, shardingValue: PreciseShardingValue<Int>): String {
        val size = availableTargetNames.size
        for (each in availableTargetNames) {
            if (each.endsWith((shardingValue.value % size).toString() + "")) {
                return each
            }
        }
        throw UnsupportedOperationException()
    }

}