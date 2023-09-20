package com.lfg.qr_day1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lfg.qr_day1.domain.Student;
import com.lfg.qr_day1.service.StudentService;
import com.lfg.qr_day1.mapper.StudentMapper;
import com.lfg.qr_day1.utius.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
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
    public Page<Student> getPage(Integer current, Integer size) {
//        return studentMapper.selectList(null);
        Page<Student> pageInfo = new Page<>(current, size);
        Page<Student> studentPage = studentMapper.selectPage(pageInfo, null);
        System.out.println(studentPage);
        Page<Student> page = this.page(studentPage, null);
        List<Student> success = studentPage.getRecords();
        Object total = studentPage.getTotal();
//        success.add(total);

        return page;
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
    //按id删除学生信息
    public void deleteStudent(Integer id) {
        if (id == null) {
            return;
        }
        studentMapper.deleteById(id);
    }

    @Override
    //批量删除学生信息
    public void deleteStudentByIds(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return;
        }
        List<Integer> list = Arrays.asList(ids);
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Student::getStudentId, list);
        studentMapper.delete(lambdaQueryWrapper);
    }


    @Override
    //按多条件模糊查询学生信息
    public List<Student> getStudentByName(Student student) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(student.getStuName() != null, Student::getStuName, student.getStuName());
        lambdaQueryWrapper.like(student.getStudentId() != null, Student::getStudentId, student.getStudentId());
        lambdaQueryWrapper.like(student.getBorthday() != null, Student::getBorthday, student.getBorthday());
        lambdaQueryWrapper.like(student.getDefac() != null, Student::getDefac, student.getDefac());
        lambdaQueryWrapper.like(student.getVersion() != null, Student::getVersion, student.getVersion());
        lambdaQueryWrapper.gt(student.getCreateTime() != null, Student::getCreateTime, student.getCreateTime());
        lambdaQueryWrapper.like(student.getDeleted() != null, Student::getDeleted, student.getDeleted());
        lambdaQueryWrapper.lt(student.getHeight() != null, Student::getHeight, student.getHeight());
        return studentMapper.selectList(lambdaQueryWrapper);
    }
}




