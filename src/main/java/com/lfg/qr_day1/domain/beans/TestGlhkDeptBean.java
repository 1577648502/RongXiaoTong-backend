package com.lfg.qr_day1.domain.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lfg.qr_day1.domain.TestGlhkDept;
import com.lfg.qr_day1.domain.TestGlhkUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestGlhkDeptBean implements Serializable {
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

    private List<TestGlhkUser> testGlhkUserList;

    public TestGlhkDeptBean(TestGlhkDept testGlhkDept) {
        this.testGlhkDeptId = testGlhkDept.getTestGlhkDeptId();
        this.deptNo = testGlhkDept.getDeptNo();
        this.deptName = testGlhkDept.getDeptName();
        this.userCount = testGlhkDept.getUserCount();
        this.deleted = testGlhkDept.getDeleted();
    }
}
