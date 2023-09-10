package com.monster.shardingjdbc.algorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 自定义Hint分片算法
 */
public class MyTableHintShardingAlgorithm implements HintShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> tables, HintShardingValue<Long> hintShardingValue) {
        Collection<String> result = new ArrayList<>();
        Collection<Long> values = hintShardingValue.getValues();
        for (Long value : values) {
            for (String table : tables) {
                String dbIndex = "" + (value % tables.size() + 1);
                if (table.endsWith(dbIndex)) {
                    result.add(table);
                }
            }
        }
        return result;
    }
}
