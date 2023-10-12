package com.lfg.rongxiaotong.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 1买家，2农民，3专家，4银行用户
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 姓名

     */
    @TableField(value = "name")
    private String name;

    /**
     * 1男 2 女
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户名

     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;


    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;
    /**
     * user普通用户，expert专家，admin管理员 , bank银行用户
     */
    @TableField(value = "role")
    private String role;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_delete")
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}