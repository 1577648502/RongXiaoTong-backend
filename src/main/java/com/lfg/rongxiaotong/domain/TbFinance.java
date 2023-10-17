package com.lfg.rongxiaotong.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName tb_finance
 */
@TableName(value ="tb_finance")
@Data
public class TbFinance implements Serializable {
    /**
     * 金融融资
     */
    @TableId(value = "finance_id", type = IdType.AUTO)
    private Integer financeId;

    /**
     * 银行
     */
    @TableField(value = "bank_id")
    private Integer bankId;

    /**
     * 登录名
     */
    @TableField(value = "own_name")
    private String ownName;

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
     * 编码
     */
    @TableField(value = "id_num")
    private String idNum;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 
     */
    @TableField(value = "money")
    private BigDecimal money;

    /**
     * 
     */
    @TableField(value = "rate")
    private BigDecimal rate;

    /**
     * 
     */
    @TableField(value = "repayment")
    private Integer repayment;

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

    /**
     * 
     */
    @TableField(value = "combination_name1")
    private String combinationName1;

    /**
     * 
     */
    @TableField(value = "combination_phone1")
    private String combinationPhone1;

    /**
     * 
     */
    @TableField(value = "combination_idnum1")
    private String combinationIdnum1;

    /**
     * 
     */
    @TableField(value = "combination_name2")
    private String combinationName2;

    /**
     * 
     */
    @TableField(value = "combination_phone2")
    private String combinationPhone2;

    /**
     * 
     */
    @TableField(value = "combination_idnum2")
    private String combinationIdnum2;

    /**
     * 
     */
    @TableField(value = "file_info")
    private String fileInfo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}