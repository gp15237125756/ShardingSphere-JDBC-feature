package com.monster.shardingjdbc.controller;

import com.monster.shardingjdbc.core.CommonRequest;
import com.monster.shardingjdbc.entity.Course;
import com.monster.shardingjdbc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CourseController {

    @Autowired
    CourseService courseService;

    @RequestMapping("/")
    @ResponseBody
    public Object getCourse(){
        CommonRequest request = new CommonRequest();
        request.setUserId(101L);
        return courseService.processBusiness0(request);
    }
}
