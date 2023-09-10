package com.monster.shardingjdbc.algorithm;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 自定义Hint分片算法
 */
public class MyDatabaseHintShardingAlgorithm implements HintShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> databases, HintShardingValue<Long> hintShardingValue) {
        Collection<String> result = new ArrayList<>();
        Collection<Long> values = hintShardingValue.getValues();
        for (Long value : values) {
            for (String dbname : databases) {
                String dbIndex = "" + (value % databases.size() + 1);
                if (dbname.endsWith(dbIndex)) {
                    result.add(dbname);
                }
            }
        }
        return result;
    }
}
