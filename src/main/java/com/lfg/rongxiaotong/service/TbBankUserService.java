package com.lfg.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbBankUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.rongxiaotong.domain.TbBankUser;
import com.lfg.rongxiaotong.utius.R;

import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【tb_bank_user(银行用户表)】的数据库操作Service
* @createDate 2023-10-17 21:16:11
*/
public interface TbBankUserService extends IService<TbBankUser> {
    R<Page<TbBankUser>> getBankUserPageList(TbBankUser tbBankUser, Integer size, Integer current, HttpServletRequest request);

    R<TbBankUser> getBankUserInfo(String bankUserId, HttpServletRequest request);

    R<String> updateBankUser(TbBankUser tbBankUser, HttpServletRequest request);

    R<String> addBankUser(TbBankUser tbBankUser, HttpServletRequest request);

    R<String> deleteBankUser(String bankUserId, HttpServletRequest request);
}
