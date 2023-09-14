package com.lfg.qr_day1;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lfg.qr_day1.domain.Student;
import com.lfg.qr_day1.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sound.midi.VoiceStatus;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootTest
class QlDay1ApplicationTests {
    @Resource
    private StudentService  studentService;

    @Test
    //获取所以学生信息
    void getAll(){
        List<Student> student = studentService.getStudent();
        for (Student student1 : student) {
            System.out.println(student1);
        }
    }
    @Test
    //获取单个学生信息，按id查询
    void getById(){
        Student student = studentService.getStudentById(1002);
        System.out.println(student);
    }
    @Test
    //添加学生信息
    void add(){
        Student student = new Student();
        student.setName("张三");
        student.setWeight(18.0);
        student.setHeight(111);
        student.setPrice(BigDecimal.valueOf(529.41));
        student.setBirthday(new Date());
        student.setCreate_time(new Date());
        student.setDeleted(0);
        studentService.addStudent(student);
    }
    @Test
    //修改学生信息
    void update(){
        Student student = new Student();
        student.setStudent_id(1L);
        student.setName("张三111");
        studentService.updateStudent(student);
    }
    @Test
    //删除学生信息，按id删除
    void delete(){
        studentService.deleteStudent(1001);
    }

    //测试条件查询
    @Test
    void queryWrapper(){
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Student> equals = lambdaQueryWrapper.eq(Student::getName, "张三");
        List<Student> student = studentService.list(equals);
        System.out.println(student);
    }
    @Test
    void getStudentByName(){
        Student student = new Student(100l,"张三",null,null,null,null,null,null);
        List<Student> studentByName = studentService.getStudentByName(student);
        System.out.println(studentByName);
    }



}
