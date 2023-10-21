package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.UserService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("/login")
    public R<User> login(@RequestBody User user, HttpServletRequest request) {
        return userService.login(user,request);
    }

    @PostMapping("/register")
    public R<String> register(@RequestBody User user ,HttpServletRequest request) {
        return userService.register(user,request);
    }
    @PostMapping("/getPageUser")
    public R<Page<User>> getPageUser(@RequestBody User user,Integer size,Integer current,HttpServletRequest request) {
        return userService.getPageUSerInfo(user,size,current,request);
    }
    @GetMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        return userService.logout(request);
    }

    @GetMapping("/info")
    public R<User> info(HttpServletRequest request) {
        return userService.info(request);
    }

    @GetMapping("/getUserImg")
    public R<String> getUserImg(String userName,HttpServletRequest request) {
        return userService.getUserImg(userName,request);
    }


    @PostMapping("/updateUser")
    public R<String> updateUser(@RequestBody User user, HttpServletRequest request) {
        return userService.updateUser(user,request);
    }
    @PostMapping("/modifyUser")
    public R<String> modifyUser(@RequestBody User user, HttpServletRequest request) {
        return userService.modifyUser(user,request);
    }
    @DeleteMapping("/delUser/")
    public R<String> delUser(@RequestBody List<Long> ids, HttpServletRequest request) {
        return userService.deleteUser(ids,request);
    }

}
