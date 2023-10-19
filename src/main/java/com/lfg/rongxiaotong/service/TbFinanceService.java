package com.lfg.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.rongxiaotong.domain.TbFinance;
import com.lfg.rongxiaotong.utius.R;

import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【tb_finance】的数据库操作Service
* @createDate 2023-10-17 19:22:13
*/
public interface TbFinanceService extends IService<TbFinance> {
    R<Page<TbFinance>> getFinancePageList(TbFinance tbFinance, Integer size, Integer current, HttpServletRequest request);

    R<TbFinance> getFinanceInfo(String bankId, HttpServletRequest request);

    R<String> updateFinance(TbFinance tbFinance, HttpServletRequest request);

    R<String> addFinance(TbFinance tbFinance, HttpServletRequest request);

    R<String> deleteFinance(String financeId, HttpServletRequest request);

}
