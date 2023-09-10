package com.monster.shardingjdbc.algorithm;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 自定义精确分库算法
 */
public class MyDatabasePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        Integer value = preciseShardingValue.getValue();
        //逻辑表名
        String logicTableName = preciseShardingValue.getLogicTableName();
        //分片字段名
        String columnName = preciseShardingValue.getColumnName();
        String dbIndex = String.valueOf(value % collection.size() + 1);
        for (String dbname : collection) {
            if (StringUtils.endsWith(dbname, dbIndex)) {
                return dbname;
            }
        }
        throw new IllegalArgumentException();
    }
}
