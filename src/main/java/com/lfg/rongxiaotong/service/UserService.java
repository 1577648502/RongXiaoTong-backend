package com.lfg.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.rongxiaotong.utius.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @author liufaguang
* @description 针对表【user】的数据库操作Service
* @createDate 2023-09-27 17:35:55
*/
public interface UserService extends IService<User> {
    /**
     * 用户登录
     *
     * @param user
     * @param request
     * @return 脱敏后用户实体类
     */
    R<User> login(User user , HttpServletRequest request);

    /**
     * 获取当前登录信息
     * @param request
     * @return
     */
    R<User> info(HttpServletRequest  request);

    /**
     *  用户注册
     * @param user
     * @return 用户注册结果字符串
     */
    R<String> register(User user);

    /**
     * 退出登录
     * @param request
     * @return 提出结果字符串
     */
    R<String> logout(HttpServletRequest request);

    /**
     * 更新用户信息
     * @param user
     * @param request
     * @return 更新结果字符串
     */
    R<String> updateUser(User user,HttpServletRequest request);

    /**
     * 分页获取用户信息
     * @param user
     * @param size
     * @param current
     * @return 分页用户类
     */
    R<Page<User>> getPageUSerInfo(User user,Integer size,Integer current,HttpServletRequest request);

    /**
     * 新增用户
     * @param user
     * @return
     */
    R<String> addUser(User user,HttpServletRequest request);

    /**
     * 删除用户
     * @param id
     * @return
     */
    R<String> deleteUser(long id, HttpServletRequest  request);

    R<String> modifyUser(User user, HttpServletRequest request);
}
