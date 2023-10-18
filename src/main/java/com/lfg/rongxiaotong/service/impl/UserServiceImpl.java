package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.mapper.UserMapper;
import com.lfg.rongxiaotong.service.UserService;
import com.lfg.rongxiaotong.utius.IsAdmin;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author liufaguang
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-09-27 17:35:55
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Override
    public R<User> login(User user, HttpServletRequest request) {
        //密码加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        System.out.println(user.getPassword());
        System.out.println(user.getUserName());
        //查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, user.getUserName());
        queryWrapper.eq(User::getPassword, user.getPassword());
        User selectOne = this.getOne(queryWrapper);

        if (selectOne != null) {
            //数据脱敏返回到前端
            User newUser = getUser(selectOne);
            //设置session
            request.getSession().setAttribute("data", newUser);
            return R.success(newUser);
        }
        return R.error("用户名或密码错误");
    }

    @Override
    public R<User> info(HttpServletRequest request) {
        User data = (User) request.getSession().getAttribute("data");
        if (data != null) {
            User byId = this.getById(data.getId());
            if (byId == null) {
                return R.error("用户未登录");
            } else {
                return R.success(byId);
            }
        } else {
            return R.error("用户未登录");
        }
    }

    @Override
    public R<String> register(User user) {
        if (user.getUserName().isEmpty() || user.getPassword().isEmpty()) {
            return R.error("用户名密码不能为空");
        }
        if (isUser(user) == null) {
            //密码加密
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            user.setCreateTime(new Date());
            user.setRole("user");
            user.setType(1);
            boolean insert = this.save(user);
            if (insert) {
                return R.success("注册成功");
            }
        }
        return R.error("用户已存在");
    }

    @Override
    public R<String> logout(HttpServletRequest request) {
        request.removeAttribute("data");
        return R.success("退出成功");
    }

    @Override
    public R<String> updateUser(User user, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (admin.equals("admin")) {
                if (user.getId() == null||user.getUserName().isEmpty()||user.getRole().isEmpty()||user.getType()==null){
                    return R.error("参数错误");
                }
                LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(User::getId, user.getId());
                User byId = this.getOne(wrapper);
                if (byId != null) {
                    User newUser = getUser(user);
                    newUser.setUpdateTime(new Date());
                    boolean update = this.updateById(newUser);
                    if (update) {
                        info(request);
                        return R.success("更新成功");
                    } else {
                        return R.error("更新失败");
                    }
                } else {
                    return R.error("用户不存在");
                }
            } else {
                return R.error("用户非管理员");
            }
        }
        return R.error("用户未登录");
    }

    @Override
    public R<Page<User>> getPageUSerInfo(User user, Integer size, Integer current, HttpServletRequest request) {


        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (admin.equals("admin")) {
                if (null == size || null == current) {
                    return R.error("参数错误");
                }
                Page<User> page = new Page<>(current, size);
                LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
                wrapper.like(null != user.getId(), User::getId, user.getId());
                wrapper.like(null != user.getRole(), User::getRole, user.getRole());
                wrapper.like(null != user.getAvatar(), User::getAvatar, user.getAvatar());
                wrapper.like(null != user.getUserName(), User::getUserName, user.getUserName());
                wrapper.like(null != user.getName(), User::getUserName, user.getName());
                wrapper.like(null != user.getSex(), User::getSex, user.getSex());
                wrapper.like(null != user.getPhone(), User::getPhone, user.getPhone());
                wrapper.like(null != user.getType(), User::getType, user.getType());
                wrapper.like(null != user.getIsDelete(), User::getIsDelete, user.getIsDelete());
                Page<User> userPage = this.page(page, wrapper);
                return R.success(userPage);
            } else {
                return R.error("用户非管理员");
            }
        }
        return R.error("用户未登录");

    }

    @Override
    public R<String> addUser(User user, HttpServletRequest request) {
        return null;
    }

    @Override
    public R<String> deleteUser(List<Long> ids, HttpServletRequest request) {
        if (ids.isEmpty()) {
            return R.error("id不能为空");
        }
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            User data = (User) request.getSession().getAttribute("data");
            if (ids.contains(data.getId()))
                return R.error("不能删除自己");


        if (admin.equals("admin")) {
            boolean update = this.removeByIds(ids);
            if (update) {
                return R.success("删除成功");
            } else {
                return R.error("删除失败");
            }
        } else {
            return R.error("用户非管理员");
        }

        }
        return R.error("用户未登录");
    }


    @Override
    public R<String> modifyUser(User user, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getId, user.getId());
            User byId = this.getOne(wrapper);
            if (byId != null) {
                User newUser = getUser(user);
                newUser.setUpdateTime(new Date());
                boolean update = this.updateById(newUser);
                if (update) {
                    info(request);
                    return R.success("更新成功");

                } else {
                    return R.error("更新失败");
                }
            } else {
                return R.error("用户不存在");
            }
        }
        return R.error("用户未登录");
    }

    @Override
    public R<String> getUserImg(String userName, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getUserName, userName);
            User byId = this.getOne(wrapper);
            if (byId != null) {
                return R.success(byId.getAvatar());
            } else {
                return R.error("用户不存在");
            }
        }
        return R.error("用户未登录");
    }

    public R<String> isUser(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, user.getUserName());
        User byId = this.getOne(wrapper);
        if (byId != null) {
            return R.error("用户已存在");
        }
        return null;
    }

    private static User getUser(User selectOne) {
        User newUser = new User();
        newUser.setId(selectOne.getId());
        newUser.setUserName(selectOne.getUserName());
        newUser.setSex(selectOne.getSex());
        newUser.setType(selectOne.getType());
        newUser.setPhone(selectOne.getPhone());
        newUser.setName(selectOne.getName());
        newUser.setCreateTime(selectOne.getCreateTime());
        newUser.setUpdateTime(selectOne.getUpdateTime());
        newUser.setAvatar(selectOne.getAvatar());
        newUser.setRole(selectOne.getRole());
        return newUser;
    }

}




