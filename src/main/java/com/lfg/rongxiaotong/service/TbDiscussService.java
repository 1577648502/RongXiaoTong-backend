package com.lfg.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbDiscuss;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.rongxiaotong.domain.TbDiscuss;
import com.lfg.rongxiaotong.utius.R;

import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【tb_discuss】的数据库操作Service
* @createDate 2023-10-13 09:54:49
*/
public interface TbDiscussService extends IService<TbDiscuss> {
    R<Page<TbDiscuss>> getDiscussPageList(TbDiscuss tbDiscuss, Integer size, Integer current, HttpServletRequest request);

    R<TbDiscuss> getDiscussInfo(String discussId, HttpServletRequest request);

    R<String> updateDiscuss(TbDiscuss tbDiscuss, HttpServletRequest request);

    R<String> addDiscuss(TbDiscuss tbDiscuss, HttpServletRequest request);

    R<String> deleteDiscuss(String discussId, HttpServletRequest request);

}
