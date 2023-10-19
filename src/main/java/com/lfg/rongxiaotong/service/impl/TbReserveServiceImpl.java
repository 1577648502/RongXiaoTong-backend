package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.rongxiaotong.domain.TbReserve;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.mapper.TbReserveMapper;
import com.lfg.rongxiaotong.service.TbReserveService;
import com.lfg.rongxiaotong.utius.IsAdmin;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【tb_reserve(专家预约表)】的数据库操作Service实现
* @createDate 2023-10-13 15:39:30
*/
@Service
public class TbReserveServiceImpl extends ServiceImpl<TbReserveMapper, TbReserve>
    implements TbReserveService{
    @Override
    public R<Page<TbReserve>> getReservePageList(TbReserve tbReserve, Integer size, Integer current, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == size || null == current) {
                return R.error("参数错误");
            }
            Page<TbReserve> page = new Page<>(current, size);
            LambdaQueryWrapper<TbReserve> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(null != tbReserve.getPlantName(), TbReserve::getPlantName, tbReserve.getPlantName());
            Page<TbReserve> tbReservePage = this.page(page, wrapper);
            return R.success(tbReservePage);
        }
        return  R.error("未登录");

    }


    @Override
    public R<TbReserve> getReserveInfo(String reserveId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == reserveId) {
                return R.error("参数错误");
            }
            return R.success(this.getById(reserveId));
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> updateReserve(TbReserve tbReserve, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbReserve ) {
                return R.error("参数错误");
            }
            boolean saved = this.updateById(tbReserve);
            if (saved) {
                return R.success("更新成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> addReserve(TbReserve tbReserve, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbReserve ) {
                return R.error("参数错误");
            }
            boolean saved = this.save(tbReserve);
            if (saved) {
                return R.success("添加成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> deleteReserve(String reserveId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == reserveId || reserveId.isEmpty()) {
                return R.error("参数错误");
            }
            boolean saved = this.removeById(reserveId);
            if (saved) {
                return R.success("删除成功");
            }
        }
        return  R.error("未登录");
    }
}




