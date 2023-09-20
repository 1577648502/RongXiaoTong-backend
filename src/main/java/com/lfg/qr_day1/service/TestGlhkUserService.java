package com.lfg.qr_day1.service;

import com.lfg.qr_day1.domain.TestGlhkUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.qr_day1.domain.beans.TestGlhkUserBean;

import java.util.List;

/**
* @author liufaguang
* @description 针对表【员工表】的数据库操作Service
* @createDate 2023-09-15 14:45:12
*/
public interface TestGlhkUserService extends IService<TestGlhkUser> {
    List<TestGlhkUserBean> getUserAll();

    TestGlhkUser getUserById(Integer testGlhkUserId);

    TestGlhkUser insertUser(TestGlhkUser testGlhkUser);

    TestGlhkUser updateUser(TestGlhkUser testGlhkUser);

    TestGlhkUser deleteUser(Integer testGlhkUserId);

}
