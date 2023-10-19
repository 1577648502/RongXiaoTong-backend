package com.lfg.rongxiaotong.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 银行表
 * @TableName tb_bank
 */
@TableName(value ="tb_bank")
@Data
public class TbBank implements Serializable {
    /**
     * 银行
     */
    @TableId(value = "bank_id", type = IdType.AUTO)
    private Integer bankId;

    /**
     * 名称
     */
    @TableField(value = "bank_name")
    private String bankName;

    /**
     * 
介绍
     */
    @TableField(value = "introduce")
    private String introduce;

    /**
     * 电话
     */
    @TableField(value = "bank_phone")
    private String bankPhone;

    /**
     * 资产
     */
    @TableField(value = "money")
    private BigDecimal money;

    /**
     * 利率
     */
    @TableField(value = "rate")
    private BigDecimal rate;

    /**
     * 债务
     */
    @TableField(value = "repayment")
    private Integer repayment;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}