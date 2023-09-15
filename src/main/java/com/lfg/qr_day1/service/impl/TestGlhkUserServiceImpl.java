package com.lfg.qr_day1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.qr_day1.domain.TestGlhkUser;
import com.lfg.qr_day1.service.TestGlhkUserService;
import com.lfg.qr_day1.mapper.TestGlhkUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author liufaguang
* @description 针对表【test_glhk_user】的数据库操作Service实现
* @createDate 2023-09-15 14:45:12
*/
@Service
public class TestGlhkUserServiceImpl extends ServiceImpl<TestGlhkUserMapper, TestGlhkUser>
    implements TestGlhkUserService{
    @Resource
    private TestGlhkUserMapper testGlhkUserMapper;

    @Override
    public List<TestGlhkUser> getUserAll() {
        return testGlhkUserMapper.selectList(null);
    }
}




