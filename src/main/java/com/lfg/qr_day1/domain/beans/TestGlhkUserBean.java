package com.lfg.qr_day1.domain.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lfg.qr_day1.domain.TestGlhkDept;
import com.lfg.qr_day1.domain.TestGlhkUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data

public class TestGlhkUserBean implements Serializable {
    /**
     * 员工
     */
    @TableId(type = IdType.AUTO)
    private Long testGlhkUserId;

    /**
     * 部门id
     */
    private Integer testGlhkDeptId;

    /**
     * 员工号
     */
    private String userNo;

    /**
     * 姓名
     */
    private String userName;


    private List<TestGlhkDept> testGlhkDeptList;

    public TestGlhkUserBean(TestGlhkUser testGlhkUser) {
       this.testGlhkDeptId = testGlhkUser.getTestGlhkDeptId();
       this.userNo = testGlhkUser.getUserNo();
       this.userName = testGlhkUser.getUserName();
       this.testGlhkUserId = testGlhkUser.getTestGlhkUserId();

    }
}
