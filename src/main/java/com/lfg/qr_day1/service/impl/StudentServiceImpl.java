package com.lfg.qr_day1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.qr_day1.domain.Student;
import com.lfg.qr_day1.service.StudentService;
import com.lfg.qr_day1.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author liufaguang
* @description 针对表【student】的数据库操作Service实现
* @createDate 2023-09-12 15:41:59
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Override
    public List<Student> getStudent() {
        List<Student> students = studentMapper.selectList(null);
        return students;
    }
}




