package com.monster.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.shardingjdbc.entity.Course;
import com.monster.shardingjdbc.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DatabaseTableShardingTests {

    @Autowired
    private CourseMapper courseMapper;
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
}
