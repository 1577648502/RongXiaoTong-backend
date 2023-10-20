package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.rongxiaotong.domain.TbExpert;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.mapper.TbExpertMapper;
import com.lfg.rongxiaotong.service.TbExpertService;
import com.lfg.rongxiaotong.utius.IsAdmin;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【tb_expert(专家)】的数据库操作Service实现
* @createDate 2023-10-13 11:36:15
*/
@Service
public class TbExpertServiceImpl extends ServiceImpl<TbExpertMapper, TbExpert>
    implements TbExpertService{

    @Override
    public R<Page<TbExpert>> getExpertPageList(TbExpert tbExpert, Integer size, Integer current, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == size || null == current) {
                return R.error("参数错误");
            }
            Page<TbExpert> page = new Page<>(current, size);
            LambdaQueryWrapper<TbExpert> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(null != tbExpert.getUserName(), TbExpert::getUserName, tbExpert.getUserName());
            Page<TbExpert> tbExpertPage = this.page(page, wrapper);
            return R.success(tbExpertPage);
        }
        return  R.error("未登录");

    }


    @Override
    public R<TbExpert> getExpertInfo(String expertId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == expertId) {
                return R.error("参数错误");
            }
            return R.success(this.getById(expertId));
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> updateExpert(TbExpert tbExpert, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbExpert ) {
                return R.error("参数错误");
            }
            boolean saved = this.updateById(tbExpert);
            if (saved) {
                return R.success("更新成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> addExpert(TbExpert tbExpert, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbExpert ) {
                return R.error("参数错误");
            }
            boolean saved = this.save(tbExpert);
            if (saved) {
                return R.success("添加成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> deleteExpert(String expertId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == expertId || expertId.isEmpty()) {
                return R.error("参数错误");
            }
            boolean saved = this.removeById(expertId);
            if (saved) {
                return R.success("删除成功");
            }
        }
        return  R.error("未登录");
    }
}




