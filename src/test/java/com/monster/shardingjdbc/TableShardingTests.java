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
class TableShardingTests {

    @Autowired
    private CourseMapper courseMapper;
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

}
