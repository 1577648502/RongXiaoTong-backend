package com.lfg.rongxiaotong.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 在线问答
 * @TableName tb_question
 */
@TableName(value ="tb_question")
@Data
public class TbQuestion implements Serializable {
    /**
     * 在线问答
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 专家
     */
    @TableField(value = "expert_name")
    private String expertName;

    /**
     * 手机号
     */
    @TableField(value = "questioner")
    private String questioner;

    /**
     * 作物详细信息
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 农作物名称
     */
    @TableField(value = "plant_name")
    private String plantName;

    /**
     * 问题标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 问题
     */
    @TableField(value = "question")
    private String question;

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