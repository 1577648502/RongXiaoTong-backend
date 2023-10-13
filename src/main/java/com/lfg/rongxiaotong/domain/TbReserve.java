package com.lfg.rongxiaotong.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 专家预约表
 * @TableName tb_reserve
 */
@TableName(value ="tb_reserve")
@Data
public class TbReserve implements Serializable {
    /**
     * 专家预约
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 专家
     */
    @TableField(value = "expert_name")
    private String expertName;

    /**
     * 咨询者
     */
    @TableField(value = "questioner")
    private String questioner;

    /**
     * 面积
     */
    @TableField(value = "area")
    private String area;

    /**
     * 土地地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 农作物名称
     */
    @TableField(value = "plant_name")
    private String plantName;

    /**
     * 土壤条件
     */
    @TableField(value = "soil_condition")
    private String soilCondition;

    /**
     * 作物条件
     */
    @TableField(value = "plant_condition")
    private String plantCondition;

    /**
     * 作物详细信息
     */
    @TableField(value = "plant_detail")
    private String plantDetail;

    /**
     * 作物详细信息
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 留言
     */
    @TableField(value = "message")
    private String message;

    /**
     * 回答
     */
    @TableField(value = "answer")
    private String answer;

    /**
     * 
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}