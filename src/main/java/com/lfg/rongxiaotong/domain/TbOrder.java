package com.lfg.rongxiaotong.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName tb_order
 */
@TableName(value ="tb_order")
@Data
public class TbOrder implements Serializable {
    /**
     * 商品
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 需求标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 期望价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 订单状态，0表示待合作，1表示待发货，2表示完成，3表示完成但未评价
     */
    @TableField(value = "order_status")
    private Integer orderStatus;

    /**
     * 1销售订单，2需求订单
     */
    @TableField(value = "type")
    private String type;

    /**
     * 订单主图
     */
    @TableField(value = "picture")
    private String picture;

    /**
     * 发起订单人
     */
    @TableField(value = "own_name")
    private String ownName;

    /**
     * 合作人名字
     */
    @TableField(value = "cooperation_name")
    private String cooperationName;

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
     * 订单收货地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 版本号 避免脏读导致的数据不一致业务情况
     */
    @TableField(value = "version")
    private Integer version;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_delete")
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}