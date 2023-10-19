package com.lfg.rongxiaotong.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 专家
 * @TableName tb_expert
 */
@TableName(value ="tb_expert")
@Data
public class TbExpert implements Serializable {
    /**
     * 登录名 专家
     */
    @TableId(value = "user_name")
    private String userName;

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
     * 职业
     */
    @TableField(value = "profession")
    private String profession;

    /**
     * 职位
     */
    @TableField(value = "position")
    private String position;

    /**
     * 属于
     */
    @TableField(value = "belong")
    private String belong;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}