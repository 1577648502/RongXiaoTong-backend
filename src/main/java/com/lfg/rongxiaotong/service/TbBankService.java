package com.lfg.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbBank;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.rongxiaotong.domain.TbBank;
import com.lfg.rongxiaotong.utius.R;

import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【tb_bank(银行表)】的数据库操作Service
* @createDate 2023-10-17 21:16:06
*/
public interface TbBankService extends IService<TbBank> {
    R<Page<TbBank>> getBankPageList(TbBank tbBank, Integer size, Integer current, HttpServletRequest request);

    R<TbBank> getBankInfo(String bankId, HttpServletRequest request);

    R<String> updateBank(TbBank tbBank, HttpServletRequest request);

    R<String> addBank(TbBank tbBank, HttpServletRequest request);

    R<String> deleteBank(String bankId, HttpServletRequest request);

}
