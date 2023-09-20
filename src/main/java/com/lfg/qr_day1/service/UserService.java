package com.lfg.qr_day1.service;

import com.lfg.qr_day1.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.qr_day1.utius.R;

import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【user】的数据库操作Service
* @createDate 2023-09-18 15:54:07
*/
public interface UserService extends IService<User> {
    R<String> login(User user);
    R<String> logout(HttpServletRequest request);
    R<User> info(HttpServletRequest request);

}
