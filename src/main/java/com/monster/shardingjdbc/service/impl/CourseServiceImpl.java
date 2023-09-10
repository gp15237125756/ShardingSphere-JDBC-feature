package com.monster.shardingjdbc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.shardingjdbc.core.BaseServiceImpl;
import com.monster.shardingjdbc.entity.Course;
import com.monster.shardingjdbc.mapper.CourseMapper;
import com.monster.shardingjdbc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends BaseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Object processBusiness() {
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("user_id", 101);
        Course course = courseMapper.selectOne(courseQueryWrapper);
        return course;
    }

}
