package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.rongxiaotong.domain.TbBank;
import com.lfg.rongxiaotong.domain.TbKnowledge;
import com.lfg.rongxiaotong.domain.TbKnowledge;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.TbKnowledgeService;
import com.lfg.rongxiaotong.mapper.TbKnowledgeMapper;
import com.lfg.rongxiaotong.utius.IsAdmin;
import com.lfg.rongxiaotong.utius.R;
import com.lfg.rongxiaotong.utius.RedisDelKeys;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liufaguang
 * @description 针对表【tb_knowledge】的数据库操作Service实现
 * @createDate 2023-10-12 22:22:35
 */
@Service
public class TbKnowledgeServiceImpl extends ServiceImpl<TbKnowledgeMapper, TbKnowledge>
        implements TbKnowledgeService {
    @Resource
    private RedisTemplate<String, Page<TbKnowledge>> redisTemplate;
    private final String redisName = "com:lfg:rongxiaotong:knowledge:";

    @Resource
    private RedisDelKeys redisDelKeys;

    @Override
    public R<Page<TbKnowledge>> getKnowledgePageList(TbKnowledge tbKnowledge, Integer size, Integer current, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == size || null == current) {
                return R.error("参数错误");
            }
            String newRedisName = redisName + request.getSession().getId() + ":" + current;
            Page<TbKnowledge> knowledgePage = redisTemplate.opsForValue().get(newRedisName);
            if (null != redisTemplate.opsForValue().get(newRedisName)&& (tbKnowledge.getOwnName().isEmpty()&&tbKnowledge.getTitle().isEmpty() &&tbKnowledge.getContent().isEmpty())) {
                return R.success(knowledgePage);
            }
            Page<TbKnowledge> page = new Page<>(current, size);
            LambdaQueryWrapper<TbKnowledge> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(null != tbKnowledge.getOwnName(), TbKnowledge::getOwnName, tbKnowledge.getOwnName());
            wrapper.like(null != tbKnowledge.getTitle(), TbKnowledge::getTitle, tbKnowledge.getTitle());
            wrapper.like(null != tbKnowledge.getContent(), TbKnowledge::getContent, tbKnowledge.getContent());
            wrapper.orderByDesc(TbKnowledge::getUpdateTime);
            Page<TbKnowledge> tbKnowledgePage = this.page(page, wrapper);
            if (tbKnowledge.getOwnName().isEmpty()&&tbKnowledge.getTitle().isEmpty()&&tbKnowledge.getContent().isEmpty()) {
                redisTemplate.opsForValue().set(newRedisName, tbKnowledgePage, 5, TimeUnit.MINUTES);
            }
            return R.success(tbKnowledgePage);
        }
        return R.error("未登录");

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
        return R.error("未登录");
    }

    @Override
    public R<String> updateKnowledge(TbKnowledge tbKnowledge, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (admin.equals("admin")) {

                if (null == tbKnowledge) {
                    return R.error("参数错误");
                }
                boolean saved = this.updateById(tbKnowledge);
                if (saved) {
                    redisDelKeys.redisDelKeys(redisName,request);
                    return R.success("更新成功");
                }
            } else {
                return R.error("用户非管理员");
            }


        }
        return R.error("未登录");
    }

    @Override
    public R<String> addKnowledge(TbKnowledge tbKnowledge, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (admin.equals("admin")) {
                if (null == tbKnowledge) {
                    return R.error("参数错误");
                }
                tbKnowledge.setOwnName(user.getUserName());
                tbKnowledge.setCreateTime(new Date());
                tbKnowledge.setUpdateTime(new Date());
                boolean saved = this.save(tbKnowledge);
                if (saved) {
                    redisDelKeys.redisDelKeys(redisName,request);
                    return R.success("添加成功");
                }
            } else {
                return R.error("用户非管理员");
            }
        }
        return R.error("未登录");
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
                    redisDelKeys.redisDelKeys(redisName,request);
                    return R.success("删除成功");
                }
            } else {
                return R.error("用户非管理员");
            }


        }
        return R.error("未登录");
    }
}




