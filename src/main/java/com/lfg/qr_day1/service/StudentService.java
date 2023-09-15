package com.lfg.qr_day1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.qr_day1.domain.Student;
import com.lfg.qr_day1.utius.R;

import java.util.List;

/**
 * @author liufaguang
 * @description 针对表【student】的数据库操作Service
 * @createDate 2023-09-12 15:41:59
 */
public interface StudentService extends IService<Student> {
    R<List<Student>> getStudent();

    Student getStudentById(Integer id);

    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Integer id);

    //批量按id删除
    void deleteStudentByIds(Integer[] ids);

    List<Student> getStudentByName(Student student);


}
