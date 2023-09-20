package com.lfg.qr_day1.controller;

import com.lfg.qr_day1.domain.TestGlhkUser;
import com.lfg.qr_day1.domain.User;
import com.lfg.qr_day1.service.TestGlhkUserService;
import com.lfg.qr_day1.service.UserService;
import com.lfg.qr_day1.utius.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private TestGlhkUserService testGlhkUserService;

    @RequestMapping("/getById")
    public R<TestGlhkUser> getById(Integer id) {
        if (id == null) {
            return R.error("id不能为空");
        }
        TestGlhkUser testGlhkUser = testGlhkUserService.getUserById(id);
        if (testGlhkUser == null) {
            return R.error("用户不存在");
        }
        return R.success(testGlhkUser);
    }

    @RequestMapping("/login")
    public R<String> login(@RequestBody User user) {
        return userService.login(user);
    }
    @RequestMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        return userService.logout(request);
    }

    @RequestMapping("/info")
    public R<User> info(HttpServletRequest request) {
        return userService.info(request);
    }

}
