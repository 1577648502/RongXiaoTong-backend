package com.lfg.qr_day1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.qr_day1.domain.TestGlhkDept;
import com.lfg.qr_day1.domain.TestGlhkUser;
import com.lfg.qr_day1.mapper.TestGlhkDeptMapper;
import com.lfg.qr_day1.service.TestGlhkUserService;
import com.lfg.qr_day1.mapper.TestGlhkUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    public List<TestGlhkUser> getUserAll() {
        return testGlhkUserMapper.selectList(null);
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




