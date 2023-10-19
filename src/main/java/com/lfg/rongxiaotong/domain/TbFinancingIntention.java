package com.lfg.rongxiaotong.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 融资意向表
 * @TableName tb_financing_intention
 */
@TableName(value ="tb_financing_intention")
@Data
public class TbFinancingIntention implements Serializable {
    /**
     * 融资意向
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 
     */
    @TableField(value = "address")
    private String address;

    /**
     * 
     */
    @TableField(value = "amount")
    private Integer amount;

    /**
     * 
     */
    @TableField(value = "application")
    private String application;

    /**
     * 
     */
    @TableField(value = "item")
    private String item;

    /**
     * 
     */
    @TableField(value = "repayment_period")
    private Integer repaymentPeriod;

    /**
     * 
     */
    @TableField(value = "area")
    private Integer area;

    /**
     * 
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}