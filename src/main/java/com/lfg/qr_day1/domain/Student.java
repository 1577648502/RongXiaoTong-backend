package com.lfg.qr_day1.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName student
 */
@TableName(value ="student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    /**
     * 主键自增
     */
    @TableId(type = IdType.AUTO)
    private Long student_id;

    /**
     * 姓名

     */
    private String name;

    /**
     * 体重
     */
    private Double weight;

    /**
     * 身高
     */
    private Integer height;

    /**
     * 余额
     */
    private BigDecimal price;

    /**
     * 创建日期
     */
    private Date create_time;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}