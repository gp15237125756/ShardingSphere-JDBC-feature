package com.monster.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.shardingjdbc.entity.Course;
import com.monster.shardingjdbc.entity.Dict;
import com.monster.shardingjdbc.entity.User;
import com.monster.shardingjdbc.mapper.CourseMapper;
import com.monster.shardingjdbc.mapper.DictMapper;
import com.monster.shardingjdbc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShardingjdbcDemoApplicationTests {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 分表不分库
     */
    @Test
    void addCourse() {
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setCname("Java" + i);
            course.setUserId(100L);
            course.setCstatus("0" + i);
            courseMapper.insert(course);
        }
    }

    /**
     * 分表不分库
     */
    @Test
    void qryCourse() {
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("cid", 901957549274693633L);
        Course course = courseMapper.selectOne(courseQueryWrapper);
        System.out.println(course);
    }

    /**
     * 分库分表insert：预期分库2,分表取决于雪花算法生成的cid是奇数还是偶数
     */
    @Test
    void addEduCourse() {
        Course course = new Course();
        course.setCname("Go");
        course.setUserId(101L);
        course.setCstatus("0");
        courseMapper.insert(course);
    }

    /**
     * 分库分表查询
     */
    @Test
    void qryEduCourse() {
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("user_id", 101);
        courseQueryWrapper.eq("cid", 901973786524385281L);
        Course course = courseMapper.selectOne(courseQueryWrapper);
        System.out.println(course);
    }

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
