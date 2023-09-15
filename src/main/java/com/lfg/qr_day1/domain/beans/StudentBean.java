package com.lfg.qr_day1.domain.beans;

import com.lfg.qr_day1.domain.Student;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class StudentBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long studentId;
    private String stuName;
    private Integer stuSex;
    private Date createTime;
    private BigDecimal height;
    private Integer stuClassId;
    private String stuClassIdName;
    public StudentBean(Long studentId, String stuName, Integer stuSex, Date createTime, BigDecimal height, Integer stuClassId, String stuClassIdName) {

        this.studentId = studentId;
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.createTime = createTime;
        this.height = height;
        this.stuClassId = stuClassId;
        this.stuClassIdName = stuClassIdName;
    }
    public StudentBean(Student student) {
        this.studentId = student.getStudentId();
        this.stuName = student.getStuName();
        this.stuSex = student.getStuSex();
        this.createTime = student.getCreateTime();
        this.height = student.getHeight();
        this.stuClassId = student.getStuClassId();
        //this.stuClassIdName = student.getStuClassIdName();
    }
}


