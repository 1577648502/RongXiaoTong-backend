package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.rongxiaotong.domain.TbBank;
import com.lfg.rongxiaotong.domain.TbFinance;
import com.lfg.rongxiaotong.domain.TbFinance;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.TbFinanceService;
import com.lfg.rongxiaotong.mapper.TbFinanceMapper;
import com.lfg.rongxiaotong.utius.IsAdmin;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author liufaguang
 * @description 针对表【tb_finance】的数据库操作Service实现
 * @createDate 2023-10-17 19:22:13
 */
@Service
public class TbFinanceServiceImpl extends ServiceImpl<TbFinanceMapper, TbFinance>
        implements TbFinanceService {
    @Resource
    private RedisTemplate<String,Page<TbFinance>> redisTemplate;
    @Override
    public R<Page<TbFinance>> getFinancePageList(TbFinance tbFinance, Integer size, Integer current, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == size || null == current) {
                return R.error("参数错误");
            }
            String redisName = "com:lfg:rongxiaotong:finance";
            Page<TbFinance> financePage = redisTemplate.opsForValue().get(redisName);
//            if (null != redisTemplate.opsForValue().get(redisName)) {
//                return R.success(financePage);
//            }
            Page<TbFinance> page = new Page<>(current, size);
            LambdaQueryWrapper<TbFinance> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(null != tbFinance.getOwnName(), TbFinance::getOwnName, tbFinance.getOwnName());
            wrapper.orderByDesc(TbFinance::getUpdateTime);
            Page<TbFinance> tbFinancePage = this.page(page, wrapper);
//            redisTemplate.opsForValue().set(redisName,tbFinancePage,60, TimeUnit.MINUTES);
            return R.success(tbFinancePage);
        }
        return R.error("未登录");

    }


    @Override
    public R<TbFinance> getFinanceInfo(String bankId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == bankId|| bankId.isEmpty()) {
                return R.error("参数错误");
            }
            LambdaQueryWrapper<TbFinance> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(TbFinance::getBankId, bankId);
            wrapper.eq(TbFinance::getOwnName,user.getUserName());
            return R.success(this.getOne(wrapper));
        }
        return R.error("未登录");
    }

    @Override
    public R<String> updateFinance(TbFinance tbFinance, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbFinance) {
                return R.error("参数错误");
            }
            boolean saved = this.updateById(tbFinance);
            if (saved) {
                return R.success("更新成功");
            }
        }
        return R.error("未登录");
    }

    @Override
    public R<String> addFinance(TbFinance tbFinance, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbFinance) {
                return R.error("参数错误");
            }
            tbFinance.setCreateTime(new Date());
            tbFinance.setUpdateTime(new Date());
            tbFinance.setStatus(0);
            tbFinance.setOwnName(user.getUserName());
            boolean saved = this.save(tbFinance);
            if (saved) {
                return R.success("添加成功");
            }
        }
        return R.error("未登录");
    }

    @Override
    public R<String> deleteFinance(String financeId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == financeId || financeId.isEmpty()) {
                return R.error("参数错误");
            }
            boolean saved = this.removeById(financeId);
            if (saved) {
                return R.success("删除成功");
            }
        }
        return R.error("未登录");
    }
}




