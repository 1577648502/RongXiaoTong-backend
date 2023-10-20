package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.rongxiaotong.domain.TbBank;
import com.lfg.rongxiaotong.domain.TbBankUser;
import com.lfg.rongxiaotong.domain.TbBankUser;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.TbBankUserService;
import com.lfg.rongxiaotong.mapper.TbBankUserMapper;
import com.lfg.rongxiaotong.utius.IsAdmin;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
* @author liufaguang
* @description 针对表【tb_bank_user(银行用户表)】的数据库操作Service实现
* @createDate 2023-10-17 21:16:11
*/
@Service
public class TbBankUserServiceImpl extends ServiceImpl<TbBankUserMapper, TbBankUser>
    implements TbBankUserService{
    @Resource
    private RedisTemplate<String,Page<TbBankUser>> redisTemplate;
    @Override
    public R<Page<TbBankUser>> getBankUserPageList(TbBankUser tbBankUser, Integer size, Integer current, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == size || null == current) {
                return R.error("参数错误");
            }
            String redisName = "com:lfg:rongxiaotong:bankuser";
            Page<TbBankUser> tbBankPage = redisTemplate.opsForValue().get(redisName);
            if (null != redisTemplate.opsForValue().get(redisName)) {
                return R.success(tbBankPage);
            }
            Page<TbBankUser> page = new Page<>(current, size);
            LambdaQueryWrapper<TbBankUser> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(null != tbBankUser.getUserName(), TbBankUser::getUserName, tbBankUser.getUserName());
            Page<TbBankUser> tbBankUserPage = this.page(page, wrapper);
            redisTemplate.opsForValue().set(redisName,tbBankUserPage,60, TimeUnit.MINUTES);
            return R.success(tbBankUserPage);
        }
        return  R.error("未登录");

    }


    @Override
    public R<TbBankUser> getBankUserInfo(String bankUserId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == bankUserId) {
                return R.error("参数错误");
            }
            return R.success(this.getById(bankUserId));
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> updateBankUser(TbBankUser tbBankUser, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbBankUser ) {
                return R.error("参数错误");
            }
            boolean saved = this.updateById(tbBankUser);
            if (saved) {
                return R.success("更新成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> addBankUser(TbBankUser tbBankUser, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbBankUser ) {
                return R.error("参数错误");
            }
            boolean saved = this.save(tbBankUser);
            if (saved) {
                return R.success("添加成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> deleteBankUser(String bankUserId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == bankUserId || bankUserId.isEmpty()) {
                return R.error("参数错误");
            }
            boolean saved = this.removeById(bankUserId);
            if (saved) {
                return R.success("删除成功");
            }
        }
        return  R.error("未登录");
    }
}




