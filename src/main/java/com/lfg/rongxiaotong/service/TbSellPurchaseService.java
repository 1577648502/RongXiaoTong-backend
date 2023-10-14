package com.lfg.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbSellPurchase;
import com.lfg.rongxiaotong.domain.TbSellPurchase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.rongxiaotong.utius.R;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
* @author liufaguang
* @description 针对表【tb_sell_purchase(卖出订单表)】的数据库操作Service
* @createDate 2023-10-14 13:26:42
*/
public interface TbSellPurchaseService extends IService<TbSellPurchase> {
    R<Page<TbSellPurchase>> getSellPurchasePageList(TbSellPurchase tbSellPurchase, Integer size, Integer current, HttpServletRequest request);

    R<TbSellPurchase> getSellPurchaseInfo(String sellPurchaseId, HttpServletRequest request);

    R<String> updateSellPurchase(TbSellPurchase tbSellPurchase, HttpServletRequest request);

    R<String> addSellPurchase(ArrayList<TbSellPurchase> tbSellPurchase, HttpServletRequest request);

    R<String> deleteSellPurchase(String sellPurchaseId, HttpServletRequest request);


}
