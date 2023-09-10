package com.monster.shardingjdbc.algorithm;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 自定义精确分表算法
 */
public class MyTablePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<Long> preciseShardingValue) {
        Long value = preciseShardingValue.getValue();
        //逻辑表名
        String logicTableName = preciseShardingValue.getLogicTableName();
        //分片字段名
        String columnName = preciseShardingValue.getColumnName();
        String dbIndex = String.valueOf(value % tableNames.size() + 1);
        for (String tableName : tableNames) {
            if (StringUtils.endsWith(tableName, dbIndex)) {
                return tableName;
            }
        }
        throw new IllegalArgumentException();
    }
}
