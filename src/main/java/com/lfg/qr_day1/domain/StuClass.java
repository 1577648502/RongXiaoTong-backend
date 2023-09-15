package com.lfg.qr_day1.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 班级
 * @TableName stu_class
 */
@TableName(value ="stu_class")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuClass implements Serializable {
    /**
     * 班级ID
     */
    @TableId(type = IdType.AUTO)
    private Long stuClassId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 班级地址
     */
    private String classAddress;

    /**
     * 
     */
    private Integer countStu;

    /**
     * 
     */
    private Date inDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}