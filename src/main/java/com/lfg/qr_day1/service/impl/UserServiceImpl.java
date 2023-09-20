package com.lfg.qr_day1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.qr_day1.domain.User;
import com.lfg.qr_day1.service.UserService;
import com.lfg.qr_day1.mapper.UserMapper;
import com.lfg.qr_day1.utius.JwtUtil;
import com.lfg.qr_day1.utius.R;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liufaguang
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-09-18 15:54:07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public R<String> login(User user) {
        System.out.println(user.getPassword());
        if (user != null) {
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
                System.out.println(newUser);
                Map<String, Object> info = new HashMap<>();
                info.put("username", selectOne.getUsername());
                info.put("id", selectOne.getUserId());
                //生成JWT字符串
                String token = JwtUtil.sign(String.valueOf(selectOne.getUserId()), info);
                return R.success(token);
            }
            return R.error("用户名或密码错误");
        }
        return null;
    }

    @Override
    public R<String> logout(HttpServletRequest request) {
        return R.success("退出成功");
    }

    @Override
    public R<User> info(HttpServletRequest request)  {
        String token = request.getHeader("token");
        if (token != null) {
            String userId = JwtUtil.getUserId(token);
            return R.success(this.getById(userId));
        }
        return R.error("用户id不能为空");
    }
}




