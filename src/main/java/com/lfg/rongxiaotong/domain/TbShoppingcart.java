package com.lfg.rongxiaotong.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName tb_shoppingcart
 */
@TableName(value ="tb_shoppingcart")
@Data
public class TbShoppingcart implements Serializable {
    /**
     * 购物车
     */
    @TableId(value = "shopping_id", type = IdType.AUTO)
    private Integer shoppingId;

    /**
     * 产品
     */
    @TableField(value = "order_id")
    private Integer orderId;

    /**
     * 
     */
    @TableField(value = "count")
    private Integer count;

    /**
     * 
     */
    @TableField(value = "own_name")
    private String ownName;

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