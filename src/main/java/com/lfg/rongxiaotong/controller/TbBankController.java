package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbBank;
import com.lfg.rongxiaotong.service.TbBankService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/bank")
public class TbBankController {
    @Resource
    private TbBankService tbBankService;
    @PostMapping("/getBankPageList")
    private R<Page<TbBank>> getBankPageList(@RequestBody TbBank tbBank, Integer size, Integer current, HttpServletRequest request){
        return tbBankService.getBankPageList(tbBank, size, current,request);
    }
    @GetMapping("/getBankInfo")
    private R<TbBank>  getBankInfo(@RequestParam String bankId, HttpServletRequest request){
        if (bankId == null){
            return R.error("农业知识id不能为空");
        }
        return tbBankService.getBankInfo(bankId,request);
    }
    @PostMapping("updateBank")
    private R<String>  updateBank(@RequestBody TbBank tbBank,HttpServletRequest request) {
        if (tbBank == null){
            return  R.error("农业知识id不能为空");
        }
        return tbBankService.updateBank(tbBank,request);
    }
    @PostMapping("addBank")
    private R<String>  addBank(@RequestBody TbBank tbBank,HttpServletRequest request) {
        if (tbBank == null){
            return R.error("农业知识id不能为空");
        }
        return tbBankService.addBank(tbBank,request);
    }
    @DeleteMapping("deleteBank")
    private R<String>  deleteBank(@RequestParam String bankId,HttpServletRequest request) {
        if (bankId == null || bankId.isEmpty()){
            return R.error("农业知识id不能为空");
        }
        return tbBankService.deleteBank(bankId,request);
    }
}
