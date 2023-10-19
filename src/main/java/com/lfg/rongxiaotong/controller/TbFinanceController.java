package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbFinance;
import com.lfg.rongxiaotong.service.TbFinanceService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/finance")
public class TbFinanceController {
    @Resource
    private TbFinanceService tbFinanceService;
    @PostMapping("/getFinancePageList")
    private R<Page<TbFinance>> getFinancePageList(@RequestBody TbFinance tbFinance, Integer size, Integer current, HttpServletRequest request){
        return tbFinanceService.getFinancePageList(tbFinance, size, current,request);
    }
    @GetMapping("/getFinanceInfo")
    private R<TbFinance>  getFinanceInfo(@RequestParam String bankId, HttpServletRequest request){
        if (bankId == null|| bankId.isEmpty()){
            return R.error("农业知识id不能为空");
        }
        return tbFinanceService.getFinanceInfo(bankId,request);
    }
    @PostMapping("updateFinance")
    private R<String>  updateFinance(@RequestBody TbFinance tbFinance,HttpServletRequest request) {
        if (tbFinance == null){
            return  R.error("农业知识id不能为空");
        }
        return tbFinanceService.updateFinance(tbFinance,request);
    }
    @PostMapping("addFinance")
    private R<String>  addFinance(@RequestBody TbFinance tbFinance,HttpServletRequest request) {
        if (tbFinance == null){
            return R.error("农业知识id不能为空");
        }
        return tbFinanceService.addFinance(tbFinance,request);
    }
    @DeleteMapping("deleteFinance")
    private R<String>  deleteFinance(@RequestParam String financeId,HttpServletRequest request) {
        if (financeId == null || financeId.isEmpty()){
            return R.error("农业知识id不能为空");
        }
        return tbFinanceService.deleteFinance(financeId,request);
    }
}
