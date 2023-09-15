package com.lfg.qr_day1.service;

import com.lfg.qr_day1.domain.TestGlhkUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author liufaguang
* @description 针对表【test_glhk_user】的数据库操作Service
* @createDate 2023-09-15 14:45:12
*/
public interface TestGlhkUserService extends IService<TestGlhkUser> {
    List<TestGlhkUser> getUserAll();

}
