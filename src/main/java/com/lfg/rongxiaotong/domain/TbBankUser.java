package com.lfg.rongxiaotong.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 银行用户表
 * @TableName tb_bank_user
 */
@TableName(value ="tb_bank_user")
@Data
public class TbBankUser implements Serializable {
    /**
     * 登录名
     */
    @TableId(value = "user_name")
    private String userName;

    /**
     * 银行
     */
    @TableField(value = "bank_id")
    private Integer bankId;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 角色
     */
    @TableField(value = "role")
    private String role;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}