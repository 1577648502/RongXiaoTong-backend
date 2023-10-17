package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbFinance;
import com.lfg.rongxiaotong.service.TbFinanceService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/finance")
public class TbFinanceController {
    @Resource
    private TbFinanceService tbFinanceService;
    @RequestMapping("/getFinancePageList")
    private R<Page<TbFinance>> getFinancePageList(@RequestBody TbFinance tbFinance, Integer size, Integer current, HttpServletRequest request){
        return tbFinanceService.getFinancePageList(tbFinance, size, current,request);
    }
    @RequestMapping("/getFinanceInfo")
    private R<TbFinance>  getFinanceInfo(@RequestParam String bankId, HttpServletRequest request){
        if (bankId == null|| bankId.isEmpty()){
            return R.error("农业知识id不能为空");
        }
        return tbFinanceService.getFinanceInfo(bankId,request);
    }
    @RequestMapping("updateFinance")
    private R<String>  updateFinance(@RequestBody TbFinance tbFinance,HttpServletRequest request) {
        if (tbFinance == null){
            return  R.error("农业知识id不能为空");
        }
        return tbFinanceService.updateFinance(tbFinance,request);
    }
    @RequestMapping("addFinance")
    private R<String>  addFinance(@RequestBody TbFinance tbFinance,HttpServletRequest request) {
        if (tbFinance == null){
            return R.error("农业知识id不能为空");
        }
        return tbFinanceService.addFinance(tbFinance,request);
    }
    @RequestMapping("deleteFinance")
    private R<String>  deleteFinance(@RequestParam String financeId,HttpServletRequest request) {
        if (financeId == null || financeId.isEmpty()){
            return R.error("农业知识id不能为空");
        }
        return tbFinanceService.deleteFinance(financeId,request);
    }
}
