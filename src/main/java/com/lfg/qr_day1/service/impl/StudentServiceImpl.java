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
    //自动注入studentMapper
    @Resource
    private StudentMapper studentMapper;

    @Override
    //获取所有学生信息List
    public List<Student> getStudent() {
        List<Student> students = studentMapper.selectList(null);
        return students;
    }

    @Override
    //按id获取学生信息
    public Student getStudentById(Integer id) {
        if (id == null) {
            return null;
        }
        Student student = studentMapper.selectById(id);
        return student;
    }

    @Override
    //添加学生信息
    public void addStudent(Student student) {
        if (student == null) {
            return;
        }
        studentMapper.insert(student);

    }

    @Override
    //修改学生信息
    public void updateStudent(Student student) {
        if (student == null) {
            return;
        }
        studentMapper.updateById(student);

    }

    @Override
    //删除学生信息
    public void deleteStudent(Integer id) {
        if (id == null) {
            return;
        }
        studentMapper.deleteById(id);
    }
}




