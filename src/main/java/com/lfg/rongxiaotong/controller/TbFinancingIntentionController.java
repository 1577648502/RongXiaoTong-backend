package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbFinancingIntention;
import com.lfg.rongxiaotong.service.TbFinancingIntentionService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/financingIntention")
public class TbFinancingIntentionController {
    @Resource
    private TbFinancingIntentionService tbFinancingIntentionService;
    @PostMapping("/getFinancingIntentionPageList")
    private R<Page<TbFinancingIntention>> getFinancingIntentionPageList(@RequestBody TbFinancingIntention tbFinancingIntention, Integer size, Integer current, HttpServletRequest request){
        return tbFinancingIntentionService.getFinancingIntentionPageList(tbFinancingIntention, size, current,request);
    }
    @GetMapping("/getFinancingIntentionInfo")
    private R<TbFinancingIntention>  getFinancingIntentionInfo(@RequestParam String financingIntentionId, HttpServletRequest request){
        if (financingIntentionId == null){
            return R.error("农业知识id不能为空");
        }
        return tbFinancingIntentionService.getFinancingIntentionInfo(financingIntentionId,request);
    }
    @PostMapping("updateFinancingIntention")
    private R<String>  updateFinancingIntention(@RequestBody TbFinancingIntention tbFinancingIntention,HttpServletRequest request) {
        if (tbFinancingIntention == null){
            return  R.error("农业知识id不能为空");
        }
        return tbFinancingIntentionService.updateFinancingIntention(tbFinancingIntention,request);
    }
    @PostMapping("addFinancingIntention")
    private R<String>  addFinancingIntention(@RequestBody TbFinancingIntention tbFinancingIntention,HttpServletRequest request) {
        if (tbFinancingIntention == null){
            return R.error("农业知识id不能为空");
        }
        return tbFinancingIntentionService.addFinancingIntention(tbFinancingIntention,request);
    }
    @DeleteMapping("deleteFinancingIntention")
    private R<String>  deleteFinancingIntention(@RequestParam String financingIntentionId,HttpServletRequest request) {
        if (financingIntentionId == null || financingIntentionId.isEmpty()){
            return R.error("农业知识id不能为空");
        }
        return tbFinancingIntentionService.deleteFinancingIntention(financingIntentionId,request);
    }
}
