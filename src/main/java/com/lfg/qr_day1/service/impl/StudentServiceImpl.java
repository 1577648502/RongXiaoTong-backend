package com.lfg.qr_day1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.qr_day1.domain.Student;
import com.lfg.qr_day1.service.StudentService;
import com.lfg.qr_day1.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        return studentMapper.selectList(null);
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

    @Override
    //按多条件模糊查询学生信息
    public List<Student> getStudentByName(Student student) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(student.getName() != null, Student::getName, student.getName());
        lambdaQueryWrapper.like(student.getStudent_id() != null, Student::getStudent_id, student.getStudent_id());
        lambdaQueryWrapper.like(student.getPrice() != null, Student::getPrice, student.getPrice());
        lambdaQueryWrapper.like(student.getHeight() != null, Student::getHeight, student.getHeight());
        lambdaQueryWrapper.like(student.getWeight() != null, Student::getWeight, student.getWeight());
        lambdaQueryWrapper.gt(student.getCreate_time() != null, Student::getCreate_time, student.getCreate_time());
        lambdaQueryWrapper.like(student.getDeleted() != null, Student::getDeleted, student.getDeleted());
        lambdaQueryWrapper.lt(student.getBirthday() != null, Student::getBirthday, student.getBirthday());
        return studentMapper.selectList(lambdaQueryWrapper);
    }
}




