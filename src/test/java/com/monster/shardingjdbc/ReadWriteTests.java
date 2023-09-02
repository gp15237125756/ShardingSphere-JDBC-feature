package com.monster.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.shardingjdbc.entity.User;
import com.monster.shardingjdbc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReadWriteTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 读写分离操作master
     */
    @Test
    void addUser() {
        User user = new User();
        user.setName("阿奇");
        user.setStatus("1");
        userMapper.insert(user);
    }

    /**
     * 读写分离写操作master
     */
    @Test
    void qryUser() {
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.eq("name", "天天");
        User user = userMapper.selectOne(userWrapper);
        System.out.println(user);
    }
}
