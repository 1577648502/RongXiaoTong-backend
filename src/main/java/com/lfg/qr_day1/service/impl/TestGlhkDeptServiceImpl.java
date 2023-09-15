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
    //获取所有部门信息和部门下的所有员工List数组
    public List<TestGlhkDeptBean> selectAll() {
        //查询数据库获取多有部门信息
        List<TestGlhkDept> testGlhkDepts = testGlhkDeptMapper.selectList(null);
        //创建存储部门信息和员工信息的数组
        List<TestGlhkDeptBean> testGlhkDeptBean = new ArrayList<>();
        //遍历所有部门信息
        for (TestGlhkDept testGlhkDept : testGlhkDepts) {
            //获取每个部门的部门id
            Long testGlhkDeptId = testGlhkDept.getTestGlhkDeptId();
            //根据部门id查询该部门下的所有员工信息
            LambdaQueryWrapper<TestGlhkUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TestGlhkUser::getTestGlhkDeptId, testGlhkDeptId);
            List<TestGlhkUser> testGlhkUsers = testGlhkUserMapper.selectList(lambdaQueryWrapper);
            //创建存储部门信息和员工信息的Bean对象
            TestGlhkDeptBean glhkDeptBean = new TestGlhkDeptBean(testGlhkDept);
            //将员工信息赋值给Bean对象
            glhkDeptBean.setTestGlhkUserList(testGlhkUsers);
            //将Bean对象添加到数组
            testGlhkDeptBean.add(glhkDeptBean);
        }
        //返回所以部门信息和部门下的员工信息
        return testGlhkDeptBean;
    }
}




