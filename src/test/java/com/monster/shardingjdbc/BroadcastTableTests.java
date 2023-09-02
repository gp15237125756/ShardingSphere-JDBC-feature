package com.monster.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.shardingjdbc.entity.Dict;
import com.monster.shardingjdbc.mapper.DictMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BroadcastTableTests {
    @Autowired
    private DictMapper dictMapper;

    /**
     * 公共表更新测试
     */
    @Test
    void addDict() {
        Dict dict = new Dict();
        dict.setKey("u_type");
        dict.setValue(1L);
        dict.setStatus("1");
        dictMapper.insert(dict);
    }

    /**
     * 公共表查询，随机路由到其中一个分库
     */
    @Test
    void qryDict() {
        QueryWrapper<Dict> dictWrapper = new QueryWrapper<>();
        dictWrapper.eq("key", "u_type");
        Dict dict = dictMapper.selectOne(dictWrapper);
        System.out.println(dict);
    }

}
