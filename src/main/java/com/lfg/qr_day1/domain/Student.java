package com.lfg.qr_day1.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生
 * @TableName student
 */
@TableName(value ="student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    /**
     * 学生
     */
    @TableId(type = IdType.AUTO)
    private Long studentId;

    /**
     * 班级
     */
    private Integer stuClassId;

    /**
     * 姓名
     */
    private String stuName;

    /**
     * 分数
     */
    private BigDecimal defac;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 生日
     */
    private Date borthday;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 逻辑删除
     */
    private Integer deleted;

    /**
     * 
     */
    private Integer stuSex;

    /**
     * 
     */
    private BigDecimal height;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}