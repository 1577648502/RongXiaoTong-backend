package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbOrder;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.TbOrderService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/order")
@RestController
public class OrderController {
    @Resource
    private TbOrderService  tbOrderService;
    @RequestMapping("/getOrderPageList")
    private R<Page<TbOrder>>  getOrderPageList(@RequestBody TbOrder tbOrder, Integer size, Integer current,HttpServletRequest  request){
        return tbOrderService.getOrderPageList(tbOrder, size, current,request);
    }
    @RequestMapping("/getOrderInfo")
    private R<TbOrder>  getOrderInfo(@RequestParam String orderId,HttpServletRequest request){
        if (orderId == null){
            return R.error("订单id不能为空");
        }
        return tbOrderService.getOrderInfo(orderId,request);
    }
    @RequestMapping("updateOrder")
    private R<String>  updateOrder(@RequestBody TbOrder tbOrder,HttpServletRequest request) {
        if (tbOrder == null){
            return  R.error("订单id不能为空");
        }
        return tbOrderService.updateOrder(tbOrder,request);
    }
    @RequestMapping("addOrder")
    private R<String>  addOrder(@RequestBody TbOrder tbOrder,HttpServletRequest request) {
     if (tbOrder == null){
            return R.error("订单id不能为空");
        }
        return tbOrderService.addOrder(tbOrder,request);
    }
    @RequestMapping("deleteOrder")
    private R<String>  deleteOrder(@RequestParam String orderId,HttpServletRequest request) {
        if (orderId == null || "".equals(orderId)){
            return R.error("订单id不能为空");
        }
        return tbOrderService.deleteOrder(orderId,request);
    }
}
