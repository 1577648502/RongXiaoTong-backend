package com.lfg.rongxiaotong.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 卖出订单表
 * @TableName tb_sell_purchase
 */
@TableName(value ="tb_sell_purchase")
@Data
public class TbSellPurchase implements Serializable {
    /**
     * 卖出订单
     */
    @TableId(value = "sell_purchase_id", type = IdType.AUTO)
    private Integer sellPurchaseId;

    /**
     * 订单
     */
    @TableField(value = "purchase_id")
    private Integer purchaseId;

    /**
     * 创建人名字
     */
    @TableField(value = "own_name")
    private String ownName;

    /**
     * 购买类型
     */
    @TableField(value = "purchase_type")
    private Integer purchaseType;

    /**
     * 单价
     */
    @TableField(value = "unin_pricee")
    private BigDecimal uninPricee;

    /**
     * 总价
     */
    @TableField(value = "sum_price")
    private BigDecimal sumPrice;

    /**
     * 订单收货地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 购买状态
     */
    @TableField(value = "purchase_status")
    private Integer purchaseStatus;

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
     * 商品订单id
     */
    @TableField(value = "order_id")
    private Integer orderId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}