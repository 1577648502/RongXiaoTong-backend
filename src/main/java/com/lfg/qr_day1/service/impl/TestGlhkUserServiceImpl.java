package com.lfg.qr_day1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.qr_day1.domain.TestGlhkDept;
import com.lfg.qr_day1.domain.TestGlhkUser;
import com.lfg.qr_day1.domain.beans.TestGlhkDeptBean;
import com.lfg.qr_day1.domain.beans.TestGlhkUserBean;
import com.lfg.qr_day1.mapper.TestGlhkDeptMapper;
import com.lfg.qr_day1.service.TestGlhkUserService;
import com.lfg.qr_day1.mapper.TestGlhkUserMapper;
import com.lfg.qr_day1.utius.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liufaguang
 * @description 针对表【test_glhk_user】的数据库操作Service实现
 * @createDate 2023-09-15 14:45:12
 */
@Service
public class TestGlhkUserServiceImpl extends ServiceImpl<TestGlhkUserMapper, TestGlhkUser>
        implements TestGlhkUserService {
    @Resource
    private TestGlhkUserMapper testGlhkUserMapper;
    @Resource
    private TestGlhkDeptMapper  testGlhkDeptMapper;

    @Override
    public List<TestGlhkUserBean> getUserAll() {
        //查询数据库获取所有用户信息
        List<TestGlhkUser> testGlhkUsers = testGlhkUserMapper.selectList(null);
        //创建存储部门信息和员工信息的数组
        List<TestGlhkUserBean> testGlhkUserBeans = new ArrayList<>();
        //遍历所有用户信息
        for (TestGlhkUser testGlhkUser : testGlhkUsers) {
            //获取每个用户的部门id
            Integer testGlhkDeptId = testGlhkUser.getTestGlhkDeptId();
            //根据用户的部门id查询部门信息
            LambdaQueryWrapper<TestGlhkDept> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TestGlhkDept::getTestGlhkDeptId, testGlhkDeptId);
            List<TestGlhkDept> testGlhkDeptList = testGlhkDeptMapper.selectList(lambdaQueryWrapper);
            //创建存储部门信息和员工信息的Bean对象
            TestGlhkUserBean glhkUserBean = new TestGlhkUserBean(testGlhkUser);
            //将部门信息赋值给Bean对象
            glhkUserBean.setTestGlhkDeptList(testGlhkDeptList);
            //将Bean对象添加到数组
            testGlhkUserBeans.add(glhkUserBean);
        }
        //返回所有员工信息和部门信息
        return testGlhkUserBeans;
    }

    @Override
    public TestGlhkUser getUserById(Integer testGlhkUserId) {
        //根据用户id查询用户信息
        TestGlhkUser testGlhkUser = this.getById(testGlhkUserId);
        return testGlhkUser;
    }

    @Override
    @Transactional
    public TestGlhkUser insertUser(TestGlhkUser testGlhkUser) {
        int insert = testGlhkUserMapper.insert(testGlhkUser);
        System.out.println(testGlhkUser);
        //插入成功，更新部门表人数
        if (insert > 0) {
            if (testGlhkUser.getTestGlhkDeptId() != null){
                LambdaQueryWrapper<TestGlhkDept> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(TestGlhkDept::getTestGlhkDeptId,testGlhkUser.getTestGlhkDeptId());
                TestGlhkDept testGlhkDept = testGlhkDeptMapper.selectOne(queryWrapper);
                if (testGlhkDept.getUserCount() != null){
                    testGlhkDept.setUserCount(testGlhkDept.getUserCount()+1);
                }else {
                    testGlhkDept.setUserCount(1);
                }
                testGlhkDeptMapper.updateById(testGlhkDept);
            }
            return testGlhkUser;
        }
        throw new RuntimeException("插入失败");
    }

    @Override
    public TestGlhkUser updateUser(TestGlhkUser testGlhkUser) {
        int update = testGlhkUserMapper.updateById(testGlhkUser);
        if (update > 0) {
            return testGlhkUser;
        }
        throw new RuntimeException("更新失败");

    }

    @Override
    @Transactional
    public TestGlhkUser deleteUser(Integer testGlhkUserId) {
        //查询用户是否存在
        TestGlhkUser testGlhkUser = testGlhkUserMapper.selectById(testGlhkUserId);
        if (testGlhkUser == null) {
            throw new RuntimeException("用户不存在");
        }
        int delete = testGlhkUserMapper.deleteById(testGlhkUserId);
        if (delete > 0) {
            if (testGlhkUser.getTestGlhkDeptId() != null){
                LambdaQueryWrapper<TestGlhkDept> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(TestGlhkDept::getTestGlhkDeptId,testGlhkUser.getTestGlhkDeptId());
                TestGlhkDept testGlhkDept = testGlhkDeptMapper.selectOne(queryWrapper);
                if (testGlhkDept.getUserCount() != null){
                    testGlhkDept.setUserCount(testGlhkDept.getUserCount()-1);
                }else {
                    testGlhkDept.setUserCount(0);
                }
                testGlhkDeptMapper.updateById(testGlhkDept);
            }
            return testGlhkUser;
        }
        throw new RuntimeException("删除失败");
    }
}




