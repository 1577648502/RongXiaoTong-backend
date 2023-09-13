package com.lfg.qr_day1;

import com.lfg.qr_day1.domain.Student;
import com.lfg.qr_day1.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootTest
class QlDay1ApplicationTests {
    @Resource
    private StudentService  studentService;

    @Test
    void contextLoads() {

    }
    @Test
    void getAll(){
        List<Student> student = studentService.getStudent();
        for (Student student1 : student) {
            System.out.println(student1);
        }
    }
    @Test
    void getById(){
        Student student = studentService.getStudentById(1002);
        System.out.println(student);
    }
    @Test
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
    void update(){
        Student student = new Student();
        student.setStudent_id(1L);
        student.setName("张三111");
        studentService.updateStudent(student);
    }
    @Test
    void delete(){
        studentService.deleteStudent(1001);
    }

}
