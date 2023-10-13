package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.rongxiaotong.domain.TbAddress;
import com.lfg.rongxiaotong.domain.TbAddress;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.TbAddressService;
import com.lfg.rongxiaotong.mapper.TbAddressMapper;
import com.lfg.rongxiaotong.utius.IsAdmin;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【tb_address】的数据库操作Service实现
* @createDate 2023-10-13 19:35:42
*/
@Service
public class TbAddressServiceImpl extends ServiceImpl<TbAddressMapper, TbAddress>
    implements TbAddressService{
    @Override
    public R<Page<TbAddress>> getAddressPageList(TbAddress tbAddress, Integer size, Integer current, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == size || null == current) {
                return R.error("参数错误");
            }
            Page<TbAddress> page = new Page<>(current, size);
            LambdaQueryWrapper<TbAddress> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(null != tbAddress.getOwnName(), TbAddress::getOwnName, tbAddress.getOwnName());
            wrapper.eq(null != tbAddress.getIsDefault(), TbAddress::getIsDefault, tbAddress.getIsDefault());
            Page<TbAddress> tbAddressPage = this.page(page, wrapper);
            return R.success(tbAddressPage);
        }
        return  R.error("未登录");

    }


    @Override
    public R<TbAddress> getAddressInfo(String addressId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == addressId) {
                return R.error("参数错误");
            }
            return R.success(this.getById(addressId));
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> updateAddress(TbAddress tbAddress, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbAddress ) {
                return R.error("参数错误");
            }
            boolean saved = this.updateById(tbAddress);
            if (saved) {
                return R.success("更新成功");
            }
        }
        return  R.error("未登录");
    }
    @Override
    public R<String> setDefaultAddress(String defaultAddress,String newAddress, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == defaultAddress || null == newAddress ) {
                return R.error("参数错误");
            }
            TbAddress tbAddress = new TbAddress();
            tbAddress.setIsDefault(0);
            LambdaQueryWrapper<TbAddress> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TbAddress::getId,defaultAddress);
            boolean saved = this.update(tbAddress,queryWrapper);
            if (saved) {
                TbAddress newTbAddress = new TbAddress();
                newTbAddress.setIsDefault(1);
                LambdaQueryWrapper<TbAddress> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(TbAddress::getId,newAddress);
                boolean update = this.update(newTbAddress, wrapper);
                if (update){
                    return R.success("更新成功");
                }
            }
            return R.error("更新失败");
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> addAddress(TbAddress tbAddress, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbAddress ) {
                return R.error("参数错误");
            }
            boolean saved = this.save(tbAddress);
            if (saved) {
                return R.success("添加成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> deleteAddress(String addressId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == addressId || addressId.isEmpty()) {
                return R.error("参数错误");
            }
            boolean saved = this.removeById(addressId);
            if (saved) {
                return R.success("删除成功");
            }
        }
        return  R.error("未登录");
    }
}




