package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbExpert;
import com.lfg.rongxiaotong.service.TbExpertService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/expert")
public class TbExpertController {
    @Resource
    private TbExpertService tbExpertService;
    @RequestMapping("/getExpertPageList")
    private R<Page<TbExpert>> getExpertPageList(@RequestBody TbExpert tbExpert, Integer size, Integer current, HttpServletRequest request){
        return tbExpertService.getExpertPageList(tbExpert, size, current,request);
    }
    @RequestMapping("/getExpertInfo")
    private R<TbExpert>  getExpertInfo(@RequestParam String expertId, HttpServletRequest request){
        if (expertId == null){
            return R.error("农业知识id不能为空");
        }
        return tbExpertService.getExpertInfo(expertId,request);
    }
    @RequestMapping("updateExpert")
    private R<String>  updateExpert(@RequestBody TbExpert tbExpert,HttpServletRequest request) {
        if (tbExpert == null){
            return  R.error("农业知识id不能为空");
        }
        return tbExpertService.updateExpert(tbExpert,request);
    }
    @RequestMapping("addExpert")
    private R<String>  addExpert(@RequestBody TbExpert tbExpert,HttpServletRequest request) {
        if (tbExpert == null){
            return R.error("农业知识id不能为空");
        }
        return tbExpertService.addExpert(tbExpert,request);
    }
    @RequestMapping("deleteExpert")
    private R<String>  deleteExpert(@RequestParam String expertId,HttpServletRequest request) {
        if (expertId == null || expertId.isEmpty()){
            return R.error("农业知识id不能为空");
        }
        return tbExpertService.deleteExpert(expertId,request);
    }
}
