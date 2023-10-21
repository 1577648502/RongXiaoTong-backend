package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.rongxiaotong.domain.TbBank;
import com.lfg.rongxiaotong.domain.TbBank;
import com.lfg.rongxiaotong.domain.TbOrder;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.TbBankService;
import com.lfg.rongxiaotong.mapper.TbBankMapper;
import com.lfg.rongxiaotong.utius.IsAdmin;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Period;
import java.util.concurrent.TimeUnit;

/**
* @author liufaguang
* @description 针对表【tb_bank(银行表)】的数据库操作Service实现
* @createDate 2023-10-17 21:16:06
*/
@Service
public class TbBankServiceImpl extends ServiceImpl<TbBankMapper, TbBank>
    implements TbBankService{
    @Resource
    private RedisTemplate<String,Page<TbBank>> redisTemplate;
    String redisName = "com:lfg:rongxiaotong:bank:";
    @Override
    public R<Page<TbBank>> getBankPageList(TbBank tbBank, Integer size, Integer current, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == size || null == current) {
                return R.error("参数错误");
            }
            Page<TbBank> users = redisTemplate.opsForValue().get(redisName+ current);
            if (null != redisTemplate.opsForValue().get(redisName)) {
                return R.success(users);
            }
            Page<TbBank> page = new Page<>(current, size);
            LambdaQueryWrapper<TbBank> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(null != tbBank.getBankName(), TbBank::getBankName, tbBank.getBankName());
            Page<TbBank> tbBankPage = this.page(page, wrapper);
            redisTemplate.opsForValue().set(redisName+current,tbBankPage,5, TimeUnit.MINUTES);
            return R.success(tbBankPage);
        }
        return  R.error("未登录");

    }


    @Override
    public R<TbBank> getBankInfo(String bankId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == bankId) {
                return R.error("参数错误");
            }
            return R.success(this.getById(bankId));
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> updateBank(TbBank tbBank, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbBank ) {
                return R.error("参数错误");
            }
            boolean saved = this.updateById(tbBank);
            if (saved) {
                return R.success("更新成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> addBank(TbBank tbBank, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbBank ) {
                return R.error("参数错误");
            }
            boolean saved = this.save(tbBank);
            if (saved) {
                return R.success("添加成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> deleteBank(String bankId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == bankId || bankId.isEmpty()) {
                return R.error("参数错误");
            }
            boolean saved = this.removeById(bankId);
            if (saved) {
                return R.success("删除成功");
            }
        }
        return  R.error("未登录");
    }
}




