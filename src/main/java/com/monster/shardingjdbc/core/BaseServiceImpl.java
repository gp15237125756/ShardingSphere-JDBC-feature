package com.monster.shardingjdbc.core;

import org.apache.shardingsphere.api.hint.HintManager;

public abstract class BaseServiceImpl implements BaseService {

    public Object processBusiness0(CommonRequest request) {
        HintManager.clear();
        HintManager instance = HintManager.getInstance();
        try {
            instance.addDatabaseShardingValue("course", request.getUserId());
            instance.addTableShardingValue("course", request.getUserId());
            //instance.setDatabaseShardingValue(request.getUserId());
            return processBusiness();
        } finally{
            instance.close();
        }
    }

    public abstract Object processBusiness();
}
