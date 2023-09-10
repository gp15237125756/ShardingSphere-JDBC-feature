package com.monster.shardingjdbc.algorithm;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 自定义复合分表算法
 */
public class MyTableComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<Integer> {
    @Override
    public Collection<String> doSharding(Collection<String> tableNames, ComplexKeysShardingValue<Integer> complexKeysShardingValue) {
        Set<String> result = new LinkedHashSet<>();
        Map<String, Collection<Integer>> columnNameAndShardingValuesMap = complexKeysShardingValue.getColumnNameAndShardingValuesMap();
        Collection<Integer> userIds = columnNameAndShardingValuesMap.get("user_id");
        Collection<Integer> cids = columnNameAndShardingValuesMap.get("cid");
        for (Integer userId: userIds){
            for (Integer cid: cids){
                String dbIndex = String.valueOf((userId + cid) % tableNames.size() + 1);
                for (String tableName : tableNames) {
                    if (StringUtils.endsWith(tableName, dbIndex)) {
                        result.add(tableName);
                    }
                }
            }
        }
        return result;
    }
}
