package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.rongxiaotong.domain.TbShoppingcart;
import com.lfg.rongxiaotong.domain.TbShoppingcart;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.TbShoppingcartService;
import com.lfg.rongxiaotong.mapper.TbShoppingcartMapper;
import com.lfg.rongxiaotong.utius.IsAdmin;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liufaguang
 * @description 针对表【tb_shoppingcart】的数据库操作Service实现
 * @createDate 2023-10-13 16:34:21
 */
@Service
public class TbShoppingcartServiceImpl extends ServiceImpl<TbShoppingcartMapper, TbShoppingcart>
        implements TbShoppingcartService {
    @Resource
    private RedisTemplate<String, List<TbShoppingcart>> redisTemplate;

    @Override
    public R<List<TbShoppingcart>> getShoppingcartPageList(TbShoppingcart tbShoppingcart, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            String redisName = "com:lfg:rongxiaotong:shoppingCart:";
            List<TbShoppingcart> RedisShoppingCart = redisTemplate.opsForValue().get(redisName + request.getSession().getId());
            if ( RedisShoppingCart!= null){
                return R.success(RedisShoppingCart);
            }
                LambdaQueryWrapper<TbShoppingcart> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(null != tbShoppingcart.getOwnName(), TbShoppingcart::getOwnName, tbShoppingcart.getOwnName());
            wrapper.orderByDesc(TbShoppingcart::getUpdateTime);
            List<TbShoppingcart> shoppingcart = this.list(wrapper);
            redisTemplate.opsForValue().set(redisName + request.getSession().getId(), shoppingcart);
            return R.success(shoppingcart);
        }
        return R.error("未登录");

    }

    @Override
    public R<TbShoppingcart> getShoppingcartInfo(String shoppingcartId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == shoppingcartId) {
                return R.error("参数错误");
            }
            return R.success(this.getById(shoppingcartId));
        }
        return R.error("未登录");
    }

    @Override
    public R<String> updateShoppingcart(TbShoppingcart tbShoppingcart, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbShoppingcart) {
                return R.error("参数错误");
            }
            tbShoppingcart.setUpdateTime(new Date());
            boolean saved = this.updateById(tbShoppingcart);
            if (saved) {
                return R.success("更新成功");
            }
        }
        return R.error("未登录");
    }

    @Override
    public R<String> addShoppingcart(TbShoppingcart tbShoppingcart, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbShoppingcart) {
                return R.error("参数错误");
            }
            //订单id和登录用户名一致判断为商品已存在，数量+1
            LambdaQueryWrapper<TbShoppingcart> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TbShoppingcart::getOrderId, tbShoppingcart.getOrderId());
            queryWrapper.eq(TbShoppingcart::getOwnName, tbShoppingcart.getOwnName());
            TbShoppingcart shoppingCart = this.getOne(queryWrapper);
            if (null != shoppingCart) {
                Integer count = shoppingCart.getCount();
                shoppingCart.setCount(count + 1);
                boolean saved = this.updateById(shoppingCart);
                if (saved) {
                    return R.success("添加成功");
                }
            }
            tbShoppingcart.setCount(1);
            tbShoppingcart.setOwnName(user.getUserName());
            tbShoppingcart.setCreateTime(new Date());
            tbShoppingcart.setUpdateTime(new Date());
            boolean saved = this.save(tbShoppingcart);
            if (saved) {
                return R.success("添加成功");
            }
        }
        return R.error("未登录");
    }

    @Override
    public R<String> deleteShoppingcart(ArrayList<String> shoppingcartId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == shoppingcartId) {
                return R.error("参数错误");
            }
            boolean saved = this.removeByIds(shoppingcartId);
            if (saved) {
                return R.success("删除成功");
            } else {
                return R.error("删除失败");
            }
        }
        return R.error("未登录");
    }
}




