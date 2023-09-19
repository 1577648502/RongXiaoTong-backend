package com.lfg.qr_day1.controller;

import com.lfg.qr_day1.domain.Student;
import com.lfg.qr_day1.domain.User;
import com.lfg.qr_day1.service.StudentService;
import com.lfg.qr_day1.service.UserService;
import com.lfg.qr_day1.utius.R;
import org.springframework.stereotype.Controller;
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
    public R<User> login(@RequestBody User user, HttpServletRequest request){
        return userService.login(user,request);
    }

    @GetMapping("/info")
    public  Object info(@RequestBody HttpServletRequest request){
        return request.getSession().getAttribute("data");
    }

}
