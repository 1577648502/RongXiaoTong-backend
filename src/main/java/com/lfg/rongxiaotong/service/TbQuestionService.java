package com.lfg.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbQuestion;
import com.lfg.rongxiaotong.domain.TbQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.rongxiaotong.utius.R;

import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【tb_question(在线问答)】的数据库操作Service
* @createDate 2023-10-13 11:34:18
*/
public interface TbQuestionService extends IService<TbQuestion> {
    R<Page<TbQuestion>> getQuestionPageList(TbQuestion tbQuestion, Integer size, Integer current, HttpServletRequest request);

    R<TbQuestion> getQuestionInfo(String questionId, HttpServletRequest request);

    R<String> updateQuestion(TbQuestion tbQuestion, HttpServletRequest request);

    R<String> addQuestion(TbQuestion tbQuestion, HttpServletRequest request);

    R<String> deleteQuestion(String questionId, HttpServletRequest request);

}
