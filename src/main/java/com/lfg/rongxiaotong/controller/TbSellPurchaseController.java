package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbSellPurchase;
import com.lfg.rongxiaotong.service.TbSellPurchaseService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("/sellPurchase")
public class TbSellPurchaseController {
    @Resource
    private TbSellPurchaseService tbSellPurchaseService;
    @RequestMapping("/getSellPurchasePageList")
    private R<Page<TbSellPurchase>> getSellPurchasePageList(@RequestBody TbSellPurchase tbSellPurchase, Integer size, Integer current, HttpServletRequest request){
        return tbSellPurchaseService.getSellPurchasePageList(tbSellPurchase, size, current,request);
    }
    @RequestMapping("/getSellPurchaseInfo")
    private R<TbSellPurchase>  getSellPurchaseInfo(@RequestParam String sellPurchaseId, HttpServletRequest request){
        if (sellPurchaseId == null){
            return R.error("农业知识id不能为空");
        }
        return tbSellPurchaseService.getSellPurchaseInfo(sellPurchaseId,request);
    }
    @RequestMapping("updateSellPurchase")
    private R<String>  updateSellPurchase(@RequestBody TbSellPurchase tbSellPurchase,HttpServletRequest request) {
        if (tbSellPurchase == null){
            return  R.error("农业知识id不能为空");
        }
        return tbSellPurchaseService.updateSellPurchase(tbSellPurchase,request);
    }
    @RequestMapping("addSellPurchase")
    private R<String>  addSellPurchase(@RequestBody ArrayList<TbSellPurchase> tbSellPurchase, HttpServletRequest request) {
        if (tbSellPurchase == null){
            return R.error("农业知识id不能为空");
        }
        return tbSellPurchaseService.addSellPurchase(tbSellPurchase,request);
    }
    @RequestMapping("deleteSellPurchase")
    private R<String>  deleteSellPurchase(@RequestParam String sellPurchaseId,HttpServletRequest request) {
        if (sellPurchaseId == null || sellPurchaseId.isEmpty()){
            return R.error("农业知识id不能为空");
        }
        return tbSellPurchaseService.deleteSellPurchase(sellPurchaseId,request);
    }
}
