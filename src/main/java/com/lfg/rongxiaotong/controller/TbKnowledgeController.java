package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbKnowledge;
import com.lfg.rongxiaotong.service.TbKnowledgeService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/knowledge")
public class TbKnowledgeController {
    @Resource
    private TbKnowledgeService tbKnowledgeService;
    @RequestMapping("/getKnowledgePageList")
    private R<Page<TbKnowledge>> getKnowledgePageList(@RequestBody TbKnowledge tbKnowledge, Integer size, Integer current, HttpServletRequest request){
        return tbKnowledgeService.getKnowledgePageList(tbKnowledge, size, current,request);
    }
    @RequestMapping("/getKnowledgeInfo")
    private R<TbKnowledge>  getKnowledgeInfo(@RequestParam String knowledgeId, HttpServletRequest request){
        if (knowledgeId == null){
            return R.error("农业知识id不能为空");
        }
        return tbKnowledgeService.getKnowledgeInfo(knowledgeId,request);
    }
    @RequestMapping("updateKnowledge")
    private R<String>  updateKnowledge(@RequestBody TbKnowledge tbKnowledge,HttpServletRequest request) {
        if (tbKnowledge == null){
            return  R.error("农业知识id不能为空");
        }
        return tbKnowledgeService.updateKnowledge(tbKnowledge,request);
    }
    @RequestMapping("addKnowledge")
    private R<String>  addKnowledge(@RequestBody TbKnowledge tbKnowledge,HttpServletRequest request) {
        if (tbKnowledge == null){
            return R.error("农业知识id不能为空");
        }
        return tbKnowledgeService.addKnowledge(tbKnowledge,request);
    }
    @RequestMapping("deleteKnowledge")
    private R<String>  deleteKnowledge(@RequestBody List<String> knowledgeIds, HttpServletRequest request) {
        if (knowledgeIds == null || knowledgeIds.isEmpty()){
            return R.error("农业知识id不能为空");
        }
        return tbKnowledgeService.deleteKnowledge(knowledgeIds,request);
    }
}
