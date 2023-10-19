package com.lfg.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.rongxiaotong.domain.TbKnowledge;
import com.lfg.rongxiaotong.utius.R;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author liufaguang
* @description 针对表【tb_knowledge】的数据库操作Service
* @createDate 2023-10-12 22:22:35
*/
public interface TbKnowledgeService extends IService<TbKnowledge> {

    R<Page<TbKnowledge>> getKnowledgePageList(TbKnowledge tbKnowledge, Integer size, Integer current, HttpServletRequest request);

    R<TbKnowledge> getKnowledgeInfo(String knowledgeId, HttpServletRequest request);

    R<String> updateKnowledge(TbKnowledge tbKnowledge, HttpServletRequest request);

    R<String> addKnowledge(TbKnowledge tbKnowledge, HttpServletRequest request);

    R<String> deleteKnowledge(List<String> knowledgeIds, HttpServletRequest request);
}
