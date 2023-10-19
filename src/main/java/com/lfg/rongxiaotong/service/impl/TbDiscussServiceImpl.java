package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.rongxiaotong.domain.TbDiscuss;
import com.lfg.rongxiaotong.domain.TbDiscuss;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.TbDiscussService;
import com.lfg.rongxiaotong.mapper.TbDiscussMapper;
import com.lfg.rongxiaotong.utius.IsAdmin;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
* @author liufaguang
* @description 针对表【tb_discuss】的数据库操作Service实现
* @createDate 2023-10-13 09:54:49
*/
@Service
public class TbDiscussServiceImpl extends ServiceImpl<TbDiscussMapper, TbDiscuss>
    implements TbDiscussService{
    @Override
    public R<Page<TbDiscuss>> getDiscussPageList(TbDiscuss tbDiscuss, Integer size, Integer current, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == size || null == current) {
                return R.error("参数错误");
            }
            Page<TbDiscuss> page = new Page<>(current, size);
            LambdaQueryWrapper<TbDiscuss> wrapper = new LambdaQueryWrapper<>();
            wrapper.orderByDesc(TbDiscuss::getCreateTime);
            wrapper.like(null != tbDiscuss.getKnowledgeId(), TbDiscuss::getKnowledgeId, tbDiscuss.getKnowledgeId());
            Page<TbDiscuss> tbDiscussPage = this.page(page, wrapper);
            return R.success(tbDiscussPage);
        }
        return  R.error("未登录");

    }


    @Override
    public R<TbDiscuss> getDiscussInfo(String discussId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == discussId) {
                return R.error("参数错误");
            }
            return R.success(this.getById(discussId));
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> updateDiscuss(TbDiscuss tbDiscuss, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbDiscuss ) {
                return R.error("参数错误");
            }
            boolean saved = this.updateById(tbDiscuss);
            if (saved) {
                return R.success("更新成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> addDiscuss(TbDiscuss tbDiscuss, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbDiscuss ) {
                return R.error("参数错误");
            }
            tbDiscuss.setOwnName(user.getUserName());
            tbDiscuss.setCreateTime(new Date());
            boolean saved = this.save(tbDiscuss);
            if (saved) {
                return R.success("添加成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> deleteDiscuss(String discussId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == discussId || discussId.isEmpty()) {
                return R.error("参数错误");
            }
            boolean saved = this.removeById(discussId);
            if (saved) {
                return R.success("删除成功");
            }
        }
        return  R.error("未登录");
    }
}




