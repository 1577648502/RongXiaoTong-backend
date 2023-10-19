package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.rongxiaotong.domain.TbKnowledge;
import com.lfg.rongxiaotong.domain.TbKnowledge;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.TbKnowledgeService;
import com.lfg.rongxiaotong.mapper.TbKnowledgeMapper;
import com.lfg.rongxiaotong.utius.IsAdmin;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
* @author liufaguang
* @description 针对表【tb_knowledge】的数据库操作Service实现
* @createDate 2023-10-12 22:22:35
*/
@Service
public class TbKnowledgeServiceImpl extends ServiceImpl<TbKnowledgeMapper, TbKnowledge>
    implements TbKnowledgeService{

    @Override
    public R<Page<TbKnowledge>> getKnowledgePageList(TbKnowledge tbKnowledge, Integer size, Integer current, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == size || null == current) {
                return R.error("参数错误");
            }
            Page<TbKnowledge> page = new Page<>(current, size);
            LambdaQueryWrapper<TbKnowledge> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(null != tbKnowledge.getOwnName(), TbKnowledge::getOwnName, tbKnowledge.getOwnName());
            wrapper.like(null != tbKnowledge.getTitle(), TbKnowledge::getTitle, tbKnowledge.getTitle());
            wrapper.like(null != tbKnowledge.getContent(), TbKnowledge::getContent, tbKnowledge.getContent());
            wrapper.orderByDesc(TbKnowledge::getUpdateTime);
            Page<TbKnowledge> tbKnowledgePage = this.page(page, wrapper);
            return R.success(tbKnowledgePage);
        }
        return  R.error("未登录");

    }


    @Override
    public R<TbKnowledge> getKnowledgeInfo(String knowledgeId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == knowledgeId) {
                return R.error("参数错误");
            }
            return R.success(this.getById(knowledgeId));
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> updateKnowledge(TbKnowledge tbKnowledge, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (admin.equals("admin")) {

                if (null == tbKnowledge ) {
                    return R.error("参数错误");
                }
                boolean saved = this.updateById(tbKnowledge);
                if (saved) {
                    return R.success("更新成功");
                }
            } else {
                return R.error("用户非管理员");
            }


        }
        return  R.error("未登录");
    }

    @Override
    public R<String> addKnowledge(TbKnowledge tbKnowledge, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (admin.equals("admin")) {
                if (null == tbKnowledge ) {
                    return R.error("参数错误");
                }
                tbKnowledge.setOwnName(user.getUserName());
                tbKnowledge.setCreateTime(new Date());
                tbKnowledge.setUpdateTime(new Date());
                boolean saved = this.save(tbKnowledge);
                if (saved) {
                    return R.success("添加成功");
                }
            } else {
                return R.error("用户非管理员");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> deleteKnowledge(List<String> knowledgeId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {

            if (admin.equals("admin")) {

                if (null == knowledgeId || knowledgeId.isEmpty()) {
                    return R.error("参数错误");
                }
                boolean saved = this.removeByIds(knowledgeId);
                if (saved) {
                    return R.success("删除成功");
                }
            } else {
                return R.error("用户非管理员");
            }


        }
        return  R.error("未登录");
    }
}




