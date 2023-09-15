package com.lfg.qr_day1.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName 员工表实体类
 */
@TableName(value ="test_glhk_user")
@Data
public class TestGlhkUser implements Serializable {
    /**
     * 员工
     */
    @TableId(type = IdType.AUTO)
    private Long testGlhkUserId;

    /**
     * 部门id
     */
    private Integer testGlhkDeptId;

    /**
     * 员工号
     */
    private String userNo;

    /**
     * 姓名
     */
    private String userName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}