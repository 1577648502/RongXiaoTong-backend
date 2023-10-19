package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbReserve;
import com.lfg.rongxiaotong.service.TbReserveService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reserve")
public class TbReserveController {
    @Resource
    private TbReserveService tbReserveService;
    @PostMapping("/getReservePageList")
    private R<Page<TbReserve>> getReservePageList(@RequestBody TbReserve tbReserve, Integer size, Integer current, HttpServletRequest request){
        return tbReserveService.getReservePageList(tbReserve, size, current,request);
    }
    @GetMapping("/getReserveInfo")
    private R<TbReserve>  getReserveInfo(@RequestParam String reserveId, HttpServletRequest request){
        if (reserveId == null){
            return R.error("农业知识id不能为空");
        }
        return tbReserveService.getReserveInfo(reserveId,request);
    }
    @PostMapping("updateReserve")
    private R<String>  updateReserve(@RequestBody TbReserve tbReserve,HttpServletRequest request) {
        if (tbReserve == null){
            return  R.error("农业知识id不能为空");
        }
        return tbReserveService.updateReserve(tbReserve,request);
    }
    @PostMapping("addReserve")
    private R<String>  addReserve(@RequestBody TbReserve tbReserve,HttpServletRequest request) {
        if (tbReserve == null){
            return R.error("农业知识id不能为空");
        }
        return tbReserveService.addReserve(tbReserve,request);
    }
    @DeleteMapping("deleteReserve")
    private R<String>  deleteReserve(@RequestParam String reserveId,HttpServletRequest request) {
        if (reserveId == null || reserveId.isEmpty()){
            return R.error("农业知识id不能为空");
        }
        return tbReserveService.deleteReserve(reserveId,request);
    }
}
