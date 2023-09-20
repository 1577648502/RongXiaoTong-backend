package com.lfg.qr_day1;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lfg.qr_day1.domain.Student;
import com.lfg.qr_day1.domain.TestGlhkUser;
import com.lfg.qr_day1.domain.beans.TestGlhkDeptBean;
import com.lfg.qr_day1.domain.beans.TestGlhkUserBean;
import com.lfg.qr_day1.service.StudentService;
import com.lfg.qr_day1.service.TestGlhkDeptService;
import com.lfg.qr_day1.service.TestGlhkUserService;
import com.lfg.qr_day1.utius.R;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootTest
class QlDay1ApplicationTests {
    @Resource
    private StudentService studentService;
    @Resource
    private TestGlhkUserService testGlhkUserService;
    @Resource
    private TestGlhkDeptService testGlhkDeptService;
    @Test
        //分页获取所以学生信息
    void getAll() {
        List<Student> student = studentService.getStudent(1,20);
        System.out.println(student);
//        Page<Student> pageInfo = new Page<>(1, 20);
//        studentService.page(pageInfo, null);
//        for (Student student:pageInfo.getRecords()){
//            System.out.println(student);
//        }
    }

    @Test
        //获取单个学生信息，按id查询
    void getById() {
        Student student = studentService.getStudentById(1002);
        System.out.println(student);
    }

    @Test
        //添加学生信息
    void add() {
        Student student = new Student();
        student.setStuName("张三");
        student.setVersion((int) 18.0);
        student.setHeight(BigDecimal.valueOf(111.01));
        student.setDefac(BigDecimal.valueOf(529.41));
        student.setCreateTime(new Date());
        student.setDeleted(0);
        studentService.addStudent(student);
    }

    @Test
        //修改学生信息
    void update() {
        Student student = new Student();
        student.setStudentId(1025L);
        student.setStuName("陈森凯");
        studentService.updateStudent(student);
    }

    @Test
        //删除学生信息，按id删除
    void delete() {
        studentService.deleteStudent(1001);
    }

    //测试条件查询
    @Test
    void queryWrapper() {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Student> equals = lambdaQueryWrapper.eq(Student::getStuName, "张三");
        List<Student> student = studentService.list(equals);
        System.out.println(student);
    }

    @Test
    void getStudentByName() {
        Student student = new Student();
        List<Student> studentByName = studentService.getStudentByName(student);
        System.out.println(studentByName);
    }

    @Test
    void getTestGlhkUserAll(){
        List<TestGlhkUserBean> userAll = testGlhkUserService.getUserAll();
        for (TestGlhkUserBean testGlhkUser : userAll)
            System.out.println(testGlhkUser);
    }
    @Test
    void getTestGlhkDeptAll(){
        List<TestGlhkDeptBean> testGlhkDeptBeans = testGlhkDeptService.selectAll();
        for (TestGlhkDeptBean testGlhkDeptBean : testGlhkDeptBeans){
            System.out.println(testGlhkDeptBean);
        }
    }

    @Test
    void addTestGlhkUser(){
        TestGlhkUser testGlhkUser = new TestGlhkUser();
        testGlhkUser.setUserName("test1");
        testGlhkUser.setUserNo("123456789");
        testGlhkUser.setTestGlhkDeptId(3);
        TestGlhkUser glhkUser = testGlhkUserService.insertUser(testGlhkUser);
        System.out.println( glhkUser);
    }
    @Test
    void delTestGlhkUser(){
        TestGlhkUser glhkUser = testGlhkUserService.deleteUser(12);
        System.out.println( glhkUser);
    }
}
