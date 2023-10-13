package com.lfg.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbReserve;
import com.lfg.rongxiaotong.domain.TbReserve;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.rongxiaotong.utius.R;

import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【tb_reserve(专家预约表)】的数据库操作Service
* @createDate 2023-10-13 15:39:30
*/
public interface TbReserveService extends IService<TbReserve> {
    R<Page<TbReserve>> getReservePageList(TbReserve tbReserve, Integer size, Integer current, HttpServletRequest request);

    R<TbReserve> getReserveInfo(String reserveId, HttpServletRequest request);

    R<String> updateReserve(TbReserve tbReserve, HttpServletRequest request);

    R<String> addReserve(TbReserve tbReserve, HttpServletRequest request);

    R<String> deleteReserve(String reserveId, HttpServletRequest request);

}
