package com.jeonguk.web.config.db

import java.util.ArrayList
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm

class TableRangeShardingAlgorithm : RangeShardingAlgorithm<Long> {

    override fun doSharding(collection: Collection<String>, rangeShardingValue: RangeShardingValue<Long>): Collection<String> {
        val size = collection.size
        val collect = ArrayList<String>()
        val valueRange = rangeShardingValue.valueRange
        for (i in valueRange.lowerEndpoint()..valueRange.upperEndpoint()) {
            for (each in collection) {
                if (each.endsWith((i % size).toString() + "")) {
                    collect.add(each)
                }
            }
        }
        return collect
    }

}