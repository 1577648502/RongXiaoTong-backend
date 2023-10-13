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
 * @TableName tb_discuss
 */
@TableName(value ="tb_discuss")
@Data
public class TbDiscuss implements Serializable {
    /**
     * 知识讨论表
     */
    @TableId(value = "discuss_id", type = IdType.AUTO)
    private Integer discussId;

    /**
     * 知识
     */
    @TableField(value = "knowledge_id")
    private Integer knowledgeId;

    /**
     * 评论者
     */
    @TableField(value = "own_name")
    private String ownName;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}