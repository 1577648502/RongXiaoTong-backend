package com.lfg.rongxiaotong.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tb_address
 */
@TableName(value ="tb_address")
@Data
public class TbAddress implements Serializable {
    /**
     * 收货地址
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 属于哪个用户的地址
     */
    @TableField(value = "own_name")
    private String ownName;

    /**
     * 收货人
     */
    @TableField(value = "consignee")
    private String consignee;

    /**
     * 收货电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 收货地址
     */
    @TableField(value = "address_detail")
    private String addressDetail;

    /**
     * 是否默认，0，不是，默认是1
     */
    @TableField(value = "is_default")
    private Integer isDefault;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}