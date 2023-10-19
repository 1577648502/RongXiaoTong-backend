package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.rongxiaotong.domain.TbSellPurchase;
import com.lfg.rongxiaotong.mapper.TbSellPurchaseMapper;
import com.lfg.rongxiaotong.service.TbSellPurchaseService;
import com.lfg.rongxiaotong.service.TbShoppingcartService;
import com.lfg.rongxiaotong.utius.IsAdmin;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author liufaguang
 * @description 针对表【tb_sell_purchase(卖出订单表)】的数据库操作Service实现
 * @createDate 2023-10-14 13:26:42
 */
@Service
public class TbSellPurchaseServiceImpl extends ServiceImpl<TbSellPurchaseMapper, TbSellPurchase>
        implements TbSellPurchaseService {
    @Resource
    private TbShoppingcartService tbShoppingcartService;
    @Override
    public R<Page<TbSellPurchase>> getSellPurchasePageList(TbSellPurchase tbSellPurchase, Integer size, Integer current, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == size || null == current) {
                return R.error("参数错误");
            }
            Page<TbSellPurchase> page = new Page<>(current, size);
            LambdaQueryWrapper<TbSellPurchase> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(null != tbSellPurchase.getOwnName(), TbSellPurchase::getOwnName, tbSellPurchase.getOwnName());
            Page<TbSellPurchase> tbSellPurchasePage = this.page(page, wrapper);
            return R.success(tbSellPurchasePage);
        }
        return R.error("未登录");

    }


    @Override
    public R<TbSellPurchase> getSellPurchaseInfo(String sellPurchaseId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == sellPurchaseId) {
                return R.error("参数错误");
            }
            return R.success(this.getById(sellPurchaseId));
        }
        return R.error("未登录");
    }

    @Override
    public R<String> updateSellPurchase(TbSellPurchase tbSellPurchase, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbSellPurchase) {
                return R.error("参数错误");
            }
            boolean saved = this.updateById(tbSellPurchase);
            if (saved) {
                return R.success("更新成功");
            }
        }
        return R.error("未登录");
    }

    @Override
    //开启事务
    @Transactional
    public R<String> addSellPurchase(ArrayList<TbSellPurchase> tbSellPurchase, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (!admin.equals("未登录")) {
            if (null == tbSellPurchase) {
                return R.error("参数错误");
            }
            for (TbSellPurchase tbSell : tbSellPurchase) {
                tbSell.setCreateTime(new Date());
                tbSell.setUpdateTime(new Date());
                arrayList.add(tbSell.getPurchaseId());
            }
            boolean saved = this.saveBatch(tbSellPurchase);
            if (saved) {
                boolean removeByIds = tbShoppingcartService.removeByIds(arrayList);
                if (removeByIds){
                    return R.success("添加成功");
                }else {
                    return R.error("出错了");
                }
            }
        }
        return R.error("未登录");
    }

    @Override
    public R<String> deleteSellPurchase(String sellPurchaseId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == sellPurchaseId || sellPurchaseId.isEmpty()) {
                return R.error("参数错误");
            }
            boolean saved = this.removeById(sellPurchaseId);
            if (saved) {
                return R.success("删除成功");
            }
        }
        return R.error("未登录");
    }
}




