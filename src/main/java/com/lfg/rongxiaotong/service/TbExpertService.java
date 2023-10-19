package com.lfg.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.rongxiaotong.domain.TbExpert;
import com.lfg.rongxiaotong.utius.R;

import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【tb_expert(专家)】的数据库操作Service
* @createDate 2023-10-13 11:36:15
*/
public interface TbExpertService extends IService<TbExpert> {
    R<Page<TbExpert>> getExpertPageList(TbExpert tbExpert, Integer size, Integer current, HttpServletRequest request);

    R<TbExpert> getExpertInfo(String expertId, HttpServletRequest request);

    R<String> updateExpert(TbExpert tbExpert, HttpServletRequest request);

    R<String> addExpert(TbExpert tbExpert, HttpServletRequest request);

    R<String> deleteExpert(String expertId, HttpServletRequest request);

}
