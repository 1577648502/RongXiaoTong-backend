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
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【tb_shoppingcart】的数据库操作Service实现
* @createDate 2023-10-13 16:34:21
*/
@Service
public class TbShoppingcartServiceImpl extends ServiceImpl<TbShoppingcartMapper, TbShoppingcart>
    implements TbShoppingcartService{
    @Override
    public R<Page<TbShoppingcart>> getShoppingcartPageList(TbShoppingcart tbShoppingcart, Integer size, Integer current, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == size || null == current) {
                return R.error("参数错误");
            }
            Page<TbShoppingcart> page = new Page<>(current, size);
            LambdaQueryWrapper<TbShoppingcart> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(null != tbShoppingcart.getOwnName(), TbShoppingcart::getOwnName, tbShoppingcart.getOwnName());
            Page<TbShoppingcart> shoppingcart = this.page(page, wrapper);
            return R.success(shoppingcart);
        }
        return  R.error("未登录");

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
        return  R.error("未登录");
    }

    @Override
    public R<String> updateShoppingcart(TbShoppingcart tbShoppingcart, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbShoppingcart ) {
                return R.error("参数错误");
            }
            boolean saved = this.updateById(tbShoppingcart);
            if (saved) {
                return R.success("更新成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> addShoppingcart(TbShoppingcart tbShoppingcart, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbShoppingcart ) {
                return R.error("参数错误");
            }
            boolean saved = this.save(tbShoppingcart);
            if (saved) {
                return R.success("添加成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> deleteShoppingcart(String shoppingcartId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == shoppingcartId || shoppingcartId.isEmpty()) {
                return R.error("参数错误");
            }
            boolean saved = this.removeById(shoppingcartId);
            if (saved) {
                return R.success("删除成功");
            }
        }
        return  R.error("未登录");
    }
}




