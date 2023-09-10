package com.monster.shardingjdbc.algorithm;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.BoundType;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 自定义范围分库算法
 */
public class MyDatabaseRangeShardingAlgorithm implements RangeShardingAlgorithm<Integer> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {
        Set<String> result = new LinkedHashSet<>();
        Integer lower = rangeShardingValue.getValueRange().lowerEndpoint();
        Integer upper = rangeShardingValue.getValueRange().upperEndpoint();
        for (Integer i = lower; i < upper; i++) {
            String dbIndex = String.valueOf(i % collection.size() + 1);
            for (String dbname : collection) {
                if (StringUtils.endsWith(dbname, dbIndex)) {
                    result.add(dbname);
                }
            }
        }
        return result;
    }
}
