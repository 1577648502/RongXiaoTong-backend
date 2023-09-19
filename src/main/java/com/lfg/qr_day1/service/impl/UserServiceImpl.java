package com.lfg.qr_day1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.qr_day1.domain.User;
import com.lfg.qr_day1.service.UserService;
import com.lfg.qr_day1.mapper.UserMapper;
import com.lfg.qr_day1.utius.R;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-09-18 15:54:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public R<User> login(User user, HttpServletRequest  request) {
        System.out.println(user.getPassword());
        if (user!=null){
            //密码加密
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            System.out.println(user.getPassword());
            //查询
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUsername, user.getUsername());
            queryWrapper.eq(User::getPassword, user.getPassword());
            User selectOne = userMapper.selectOne(queryWrapper);
            if (selectOne != null) {
                //数据脱敏返回到前端
                User newUser = new User();
                newUser.setUserId(selectOne.getUserId());
                newUser.setUsername(selectOne.getUsername());
                newUser.setEmail(selectOne.getEmail());
                newUser.setFullName(selectOne.getFullName());
                newUser.setPhoneNumber(selectOne.getPhoneNumber());
                //设置session
                request.getSession().setAttribute("data", newUser);
                System.out.println(newUser);
                return R.success(newUser);
            }
            return R.error("用户名或密码错误");
        }
        return null;
    }
}




