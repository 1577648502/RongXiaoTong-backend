package com.lfg.qr_day1.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.qr_day1.domain.Student;
import com.lfg.qr_day1.service.StudentService;
import com.lfg.qr_day1.utius.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService studentService;

    @RequestMapping("/getPage")
    public R<Page<Student>> getPage(Integer page, Integer limit, String sort, String title) {
        Page<Student> students = studentService.getPage(page, limit, sort, title);
        if (students == null) {
            return R.error("获取学生列表失败");
        }
        return R.success(students);
    }
}
