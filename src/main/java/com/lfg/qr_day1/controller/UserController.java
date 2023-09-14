package com.lfg.qr_day1.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.qr_day1.domain.Student;
import com.lfg.qr_day1.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private StudentService studentService;
    @GetMapping("/getUser")
    public List<Student> getUser() {
        List<Student> serviceStudent = studentService.getStudent();
        System.out.println(serviceStudent);
        return serviceStudent;
    }

}
