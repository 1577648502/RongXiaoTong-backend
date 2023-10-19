package com.lfg.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.rongxiaotong.domain.TbFinancingIntention;
import com.lfg.rongxiaotong.utius.R;

import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【tb_financing_intention(融资意向表)】的数据库操作Service
* @createDate 2023-10-17 19:22:18
*/
public interface TbFinancingIntentionService extends IService<TbFinancingIntention> {
    R<Page<TbFinancingIntention>> getFinancingIntentionPageList(TbFinancingIntention tbFinancingIntention, Integer size, Integer current, HttpServletRequest request);

    R<TbFinancingIntention> getFinancingIntentionInfo(String financingIntentionId, HttpServletRequest request);

    R<String> updateFinancingIntention(TbFinancingIntention tbFinancingIntention, HttpServletRequest request);

    R<String> addFinancingIntention(TbFinancingIntention tbFinancingIntention, HttpServletRequest request);

    R<String> deleteFinancingIntention(String financingIntentionId, HttpServletRequest request);

}
