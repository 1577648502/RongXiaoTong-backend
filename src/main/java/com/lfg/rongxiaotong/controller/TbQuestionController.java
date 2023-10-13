package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbQuestion;
import com.lfg.rongxiaotong.service.TbQuestionService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/question")
public class TbQuestionController {
    @Resource
    private TbQuestionService tbQuestionService;
    @RequestMapping("/getQuestionPageList")
    private R<Page<TbQuestion>> getQuestionPageList(@RequestBody TbQuestion tbQuestion, Integer size, Integer current, HttpServletRequest request){
        return tbQuestionService.getQuestionPageList(tbQuestion, size, current,request);
    }
    @RequestMapping("/getQuestionInfo")
    private R<TbQuestion>  getQuestionInfo(@RequestParam String questionId, HttpServletRequest request){
        if (questionId == null){
            return R.error("农业知识id不能为空");
        }
        return tbQuestionService.getQuestionInfo(questionId,request);
    }
    @RequestMapping("updateQuestion")
    private R<String>  updateQuestion(@RequestBody TbQuestion tbQuestion,HttpServletRequest request) {
        if (tbQuestion == null){
            return  R.error("农业知识id不能为空");
        }
        return tbQuestionService.updateQuestion(tbQuestion,request);
    }
    @RequestMapping("addQuestion")
    private R<String>  addQuestion(@RequestBody TbQuestion tbQuestion,HttpServletRequest request) {
        if (tbQuestion == null){
            return R.error("农业知识id不能为空");
        }
        return tbQuestionService.addQuestion(tbQuestion,request);
    }
    @RequestMapping("deleteQuestion")
    private R<String>  deleteQuestion(@RequestParam String questionId,HttpServletRequest request) {
        if (questionId == null || questionId.isEmpty()){
            return R.error("农业知识id不能为空");
        }
        return tbQuestionService.deleteQuestion(questionId,request);
    }
}
