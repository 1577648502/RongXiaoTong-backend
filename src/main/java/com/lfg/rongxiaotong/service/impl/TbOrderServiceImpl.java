package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lfg.rongxiaotong.domain.TbOrder;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.mapper.TbOrderMapper;
import com.lfg.rongxiaotong.service.TbOrderService;
import com.lfg.rongxiaotong.utius.IsAdmin;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liufaguang
 * @description 针对表【tb_order】的数据库操作Service实现
 * @createDate 2023-10-07 15:02:43
 */
@Service
public class TbOrderServiceImpl extends ServiceImpl<TbOrderMapper, TbOrder>
        implements TbOrderService {
    @Resource
    private RedisTemplate<String, Page<TbOrder>> redisTemplate;
    private final String redisName = "com:lfg:rongxiaotong:order:";

    @Override
    public R<Page<TbOrder>> getOrderPageList(TbOrder tbOrder, Integer size, Integer current, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == size || null == current) {
                return R.error("参数错误");
            }
            Page<TbOrder> orderPage = redisTemplate.opsForValue().get(redisName+ current);
            if (null != redisTemplate.opsForValue().get(redisName)) {
                return R.success(orderPage);
            }
            Page<TbOrder> page = new Page<>(current, size);
            LambdaQueryWrapper<TbOrder> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(null != tbOrder.getOwnName(), TbOrder::getOwnName, tbOrder.getOwnName());
            wrapper.orderByDesc(TbOrder::getUpdateTime);
            Page<TbOrder> tbOrderPage = this.page(page, wrapper);
            redisTemplate.opsForValue().set(redisName+current, tbOrderPage, 60, TimeUnit.MINUTES);
            return R.success(tbOrderPage);

        }
        return R.error("未登录");

    }

    @Override
    public R<TbOrder> getOrderInfo(String orderId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {

            if (null == orderId) {
                return R.error("参数错误");
            }
            return R.success(this.getById(orderId));
        }
        return R.error("未登录");
    }

    @Override
    public R<String> updateOrder(TbOrder tbOrder, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (admin.equals("admin")) {
                if (null == tbOrder) {
                    return R.error("参数错误");
                }
                boolean saved = this.updateById(tbOrder);
                if (saved) {
                    return R.success("更新成功");
                }
            } else {
                return R.error("用户非管理员");
            }
        }
        return R.error("未登录");
    }

    @Override
    public R<String> addOrder(TbOrder tbOrder, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (admin.equals("admin")) {
                if (null == tbOrder) {
                    return R.error("参数错误");
                }
                tbOrder.setOwnName(user.getUserName());
                tbOrder.setCreateTime(new Date());
                tbOrder.setUpdateTime(new Date());
                tbOrder.setIsDelete(0);
                boolean saved = this.save(tbOrder);
                if (saved) {
                    return R.success("添加成功");
                }
            }
            return R.error("用户非管理员");
        }
        return R.error("未登录");
    }

    @Override
    public R<String> deleteOrder(List<Long> orderIds, HttpServletRequest request) {
        if (orderIds.isEmpty()) {
            return R.error("id不能为空");
        }
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (admin.equals("admin")) {
                boolean update = this.removeByIds(orderIds);
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


}