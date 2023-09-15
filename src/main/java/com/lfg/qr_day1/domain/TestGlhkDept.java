package com.lfg.qr_day1.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * 
 * @TableName 部门表实体类
 */
@TableName(value ="test_glhk_dept")
@Data
public class TestGlhkDept implements Serializable {
    /**
     * 部门id
     */
    @TableId(type = IdType.AUTO)
    private Long testGlhkDeptId;
    /**
     * 部门号
     */
    private String deptNo;
    /**
     * 名称
     */
    private String deptName;
    /**
     * 总人数
     */
    private Integer userCount;
    /**
     * 逻辑删除
     */
    private Integer deleted;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}