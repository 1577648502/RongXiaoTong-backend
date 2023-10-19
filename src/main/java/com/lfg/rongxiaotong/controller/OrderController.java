package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbOrder;
import com.lfg.rongxiaotong.service.TbOrderService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {
    @Resource
    private TbOrderService  tbOrderService;
    @PostMapping("/getOrderPageList")
    private R<Page<TbOrder>>  getOrderPageList(@RequestBody TbOrder tbOrder, Integer size, Integer current,HttpServletRequest  request){
        return tbOrderService.getOrderPageList(tbOrder, size, current,request);
    }
    @GetMapping("/getOrderInfo")
    private R<TbOrder>  getOrderInfo(@RequestParam String orderId,HttpServletRequest request){
        if (orderId == null){
            return R.error("订单id不能为空");
        }
        return tbOrderService.getOrderInfo(orderId,request);
    }
    @PostMapping("updateOrder")
    private R<String>  updateOrder(@RequestBody TbOrder tbOrder,HttpServletRequest request) {
        if (tbOrder == null){
            return  R.error("订单id不能为空");
        }
        return tbOrderService.updateOrder(tbOrder,request);
    }
    @PostMapping("addOrder")
    private R<String>  addOrder(@RequestBody TbOrder tbOrder,HttpServletRequest request) {
     if (tbOrder == null){
            return R.error("订单id不能为空");
        }
        return tbOrderService.addOrder(tbOrder,request);
    }
    @DeleteMapping("deleteOrder")
    private R<String>  deleteOrder(@RequestBody List<Long> orderIds, HttpServletRequest request) {
        if (orderIds == null || orderIds.size() == 0){
            return R.error("订单id不能为空");
        }
        return tbOrderService.deleteOrder(orderIds,request);
    }
}
