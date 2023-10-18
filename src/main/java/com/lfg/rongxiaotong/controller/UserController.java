package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.UserService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("/login")
    public R<User> login(@RequestBody User user, HttpServletRequest request) {
        return userService.login(user,request);
    }

    @RequestMapping("/register")
    public R<String> register(@RequestBody User user ) {
        return userService.register(user);
    }
    @RequestMapping("/getPageUser")
    public R<Page<User>> getPageUser(@RequestBody User user,Integer size,Integer current,HttpServletRequest request) {
        return userService.getPageUSerInfo(user,size,current,request);
    }
    @RequestMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        return userService.logout(request);
    }

    @RequestMapping("/info")
    public R<User> info(HttpServletRequest request) {
        return userService.info(request);
    }

    @RequestMapping("/getUserImg")
    public R<String> getUserImg(String userName,HttpServletRequest request) {
        return userService.getUserImg(userName,request);
    }


    @RequestMapping("/updateUser")
    public R<String> updateUser(@RequestBody User user, HttpServletRequest request) {
        return userService.updateUser(user,request);
    }
    @RequestMapping("/modifyUser")
    public R<String> modifyUser(@RequestBody User user, HttpServletRequest request) {
        return userService.modifyUser(user,request);
    }
    @RequestMapping("/delUser/")
    public R<String> delUser(@RequestBody List<Long> ids, HttpServletRequest request) {
        return userService.deleteUser(ids,request);
    }

}
