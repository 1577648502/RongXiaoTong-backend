package com.lfg.qr_day1.domain.beans;

import com.lfg.qr_day1.domain.StuClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuClassBean implements Serializable {

    private static final long serialVersionUID = 1L;
    //班级id
    private Integer stuClassId;
    //学生数量
    private Integer countStu;
    //班级名称
    private String stuClassName;
    //入学时间
    private Date inDate;
    private List<StudentBean> studentBeanList;



}
