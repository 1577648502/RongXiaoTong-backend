package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.rongxiaotong.domain.TbBank;
import com.lfg.rongxiaotong.domain.TbFinancingIntention;
import com.lfg.rongxiaotong.domain.TbFinancingIntention;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.TbFinancingIntentionService;
import com.lfg.rongxiaotong.mapper.TbFinancingIntentionMapper;
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
 * @description 针对表【tb_financing_intention(融资意向表)】的数据库操作Service实现
 * @createDate 2023-10-17 19:22:18
 */
@Service
public class TbFinancingIntentionServiceImpl extends ServiceImpl<TbFinancingIntentionMapper, TbFinancingIntention>
        implements TbFinancingIntentionService {
    @Resource
    private RedisTemplate<String,Page<TbFinancingIntention>> redisTemplate;
    @Override
    public R<Page<TbFinancingIntention>> getFinancingIntentionPageList(TbFinancingIntention tbFinancingIntention, Integer size, Integer current, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == size || null == current) {
                return R.error("参数错误");
            }
            String redisName = "com:lfg:rongxiaotong:financingIntention";
            Page<TbFinancingIntention> financingIntentionPage = redisTemplate.opsForValue().get(redisName);
//            if (null != redisTemplate.opsForValue().get(redisName)) {
//                return R.success(financingIntentionPage);
//            }
            Page<TbFinancingIntention> page = new Page<>(current, size);
            LambdaQueryWrapper<TbFinancingIntention> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(tbFinancingIntention.getUserName() != null, TbFinancingIntention::getUserName, user.getUserName());
            wrapper.orderByDesc(TbFinancingIntention::getUpdateTime);
            Page<TbFinancingIntention> tbFinancingIntentionPage = this.page(page, wrapper);
//            redisTemplate.opsForValue().set(redisName,tbFinancingIntentionPage,60, TimeUnit.MINUTES);
            return R.success(tbFinancingIntentionPage);
        }
        return R.error("未登录");

    }


    @Override
    public R<TbFinancingIntention> getFinancingIntentionInfo(String financingIntentionId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == financingIntentionId) {
                return R.error("参数错误");
            }
            return R.success(this.getById(financingIntentionId));
        }
        return R.error("未登录");
    }

    @Override
    public R<String> updateFinancingIntention(TbFinancingIntention tbFinancingIntention, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbFinancingIntention) {
                return R.error("参数错误");
            }
            boolean saved = this.updateById(tbFinancingIntention);
            if (saved) {
                return R.success("更新成功");
            }
        }
        return R.error("未登录");
    }

    @Override
    public R<String> addFinancingIntention(TbFinancingIntention tbFinancingIntention, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbFinancingIntention) {
                return R.error("参数错误");
            }
            tbFinancingIntention.setUserName(user.getUserName());
            tbFinancingIntention.setCreateTime(new Date());
            tbFinancingIntention.setUpdateTime(new Date());
            boolean saved = this.save(tbFinancingIntention);
            if (saved) {
                return R.success("添加成功");
            }
        }
        return R.error("未登录");
    }

    @Override
    public R<String> deleteFinancingIntention(String financingIntentionId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == financingIntentionId || financingIntentionId.isEmpty()) {
                return R.error("参数错误");
            }
            boolean saved = this.removeById(financingIntentionId);
            if (saved) {
                return R.success("删除成功");
            }
        }
        return R.error("未登录");
    }
}




