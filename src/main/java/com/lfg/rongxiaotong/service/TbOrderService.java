package com.lfg.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.rongxiaotong.domain.TbOrder;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.core.annotation.Order;

import javax.servlet.http.HttpServletRequest;


/**
 * @author liufaguang
 * @description 针对表【tb_order】的数据库操作Service
 * @createDate 2023-10-07 15:02:43
 */
public interface TbOrderService extends IService<TbOrder> {
    R<Page<TbOrder>> getOrderPageList(TbOrder tbOrder, Integer size, Integer current, HttpServletRequest request);

    R<TbOrder> getOrderInfo(String orderId, HttpServletRequest request);

    R<String> updateOrder(TbOrder tbOrder, HttpServletRequest request);

    R<String> addOrder(TbOrder tbOrder, HttpServletRequest request);

    R<String> deleteOrder(String orderId, HttpServletRequest request);
}
