package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbDiscuss;
import com.lfg.rongxiaotong.service.TbDiscussService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/discuss")
public class TbDiscussController {
    @Resource
    private TbDiscussService tbDiscussService;
    @PostMapping("/getDiscussPageList")
    private R<Page<TbDiscuss>> getDiscussPageList(@RequestBody TbDiscuss tbDiscuss, Integer size, Integer current, HttpServletRequest request){
        return tbDiscussService.getDiscussPageList(tbDiscuss, size, current,request);
    }
    @GetMapping("/getDiscussInfo")
    private R<TbDiscuss>  getDiscussInfo(@RequestParam String discussId, HttpServletRequest request){
        if (discussId == null){
            return R.error("农业知识id不能为空");
        }
        return tbDiscussService.getDiscussInfo(discussId,request);
    }
    @PostMapping("updateDiscuss")
    private R<String>  updateDiscuss(@RequestBody TbDiscuss tbDiscuss,HttpServletRequest request) {
        if (tbDiscuss == null){
            return  R.error("农业知识id不能为空");
        }
        return tbDiscussService.updateDiscuss(tbDiscuss,request);
    }
    @PostMapping("addDiscuss")
    private R<String>  addDiscuss(@RequestBody TbDiscuss tbDiscuss,HttpServletRequest request) {
        if (tbDiscuss == null){
            return R.error("农业知识id不能为空");
        }
        return tbDiscussService.addDiscuss(tbDiscuss,request);
    }
    @DeleteMapping("deleteDiscuss")
    private R<String>  deleteDiscuss(@RequestParam String discussId,HttpServletRequest request) {
        if (discussId == null || discussId.isEmpty()){
            return R.error("农业知识id不能为空");
        }
        return tbDiscussService.deleteDiscuss(discussId,request);
    }
}
