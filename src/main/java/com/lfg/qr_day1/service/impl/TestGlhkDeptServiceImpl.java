package com.lfg.qr_day1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.qr_day1.domain.TestGlhkDept;
import com.lfg.qr_day1.domain.TestGlhkUser;
import com.lfg.qr_day1.domain.beans.TestGlhkDeptBean;
import com.lfg.qr_day1.mapper.TestGlhkUserMapper;
import com.lfg.qr_day1.service.TestGlhkDeptService;
import com.lfg.qr_day1.mapper.TestGlhkDeptMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author liufaguang
 * @description 针对表【test_glhk_dept】的数据库操作Service实现
 * @createDate 2023-09-15 14:45:12
 */
@Service
public class TestGlhkDeptServiceImpl extends ServiceImpl<TestGlhkDeptMapper, TestGlhkDept>
        implements TestGlhkDeptService {
    @Resource
    private TestGlhkDeptMapper testGlhkDeptMapper;
    @Resource
    private TestGlhkUserMapper testGlhkUserMapper;

    @Override
    public List<TestGlhkDeptBean> selectAll() {
        List<TestGlhkDept> testGlhkDepts = testGlhkDeptMapper.selectList(null);
        List<TestGlhkDeptBean> testGlhkDeptBean = new ArrayList<>();
        for (TestGlhkDept testGlhkDept : testGlhkDepts) {
            Long testGlhkDeptId = testGlhkDept.getTestGlhkDeptId();
            LambdaQueryWrapper<TestGlhkUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TestGlhkUser::getTestGlhkDeptId, testGlhkDeptId);
            List<TestGlhkUser> testGlhkUsers = testGlhkUserMapper.selectList(lambdaQueryWrapper);
            TestGlhkDeptBean glhkDeptBean = new TestGlhkDeptBean(testGlhkDept);
            glhkDeptBean.setTestGlhkUserList(testGlhkUsers);
            testGlhkDeptBean.add(glhkDeptBean);
        }
        return testGlhkDeptBean;
    }
}




