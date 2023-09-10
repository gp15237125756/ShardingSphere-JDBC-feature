package com.monster.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.shardingjdbc.entity.Course;
import com.monster.shardingjdbc.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DatabaseHintShardingTests {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * hint分库测试
     */
    @Test
    void qryEduCourse() {
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("user_id", 101);
        courseQueryWrapper.eq("cid", 901);
        Course course = courseMapper.selectOne(courseQueryWrapper);
        System.out.println(course);
    }

}
