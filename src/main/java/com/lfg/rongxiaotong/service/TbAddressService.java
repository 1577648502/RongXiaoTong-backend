package com.lfg.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.rongxiaotong.domain.TbAddress;
import com.lfg.rongxiaotong.utius.R;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author liufaguang
* @description 针对表【tb_address】的数据库操作Service
* @createDate 2023-10-13 19:35:42
*/
public interface TbAddressService extends IService<TbAddress> {
    R<List<TbAddress>> getAddressPageList(TbAddress tbAddress, HttpServletRequest request);

    R<TbAddress> getAddressInfo(String addressId, HttpServletRequest request);

    R<String> setDefaultAddress(String detDefaultAddress,String newAddress, HttpServletRequest request);

    R<String> updateAddress(TbAddress tbAddress, HttpServletRequest request);

    R<String> addAddress(TbAddress tbAddress, HttpServletRequest request);

    R<String> deleteAddress(String addressId, HttpServletRequest request);


}
