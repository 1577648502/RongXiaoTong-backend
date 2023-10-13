package com.lfg.rongxiaotong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbShoppingcart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.rongxiaotong.utius.R;

import javax.servlet.http.HttpServletRequest;

/**
* @author liufaguang
* @description 针对表【tb_shoppingcart】的数据库操作Service
* @createDate 2023-10-13 16:34:21
*/
public interface TbShoppingcartService extends IService<TbShoppingcart> {
    R<Page<TbShoppingcart>> getShoppingcartPageList(TbShoppingcart tbShoppingcart, Integer size, Integer current, HttpServletRequest request);

    R<TbShoppingcart> getShoppingcartInfo(String tbShoppingcartId, HttpServletRequest request);

    R<String> updateShoppingcart(TbShoppingcart tbShoppingcart, HttpServletRequest request);

    R<String> addShoppingcart(TbShoppingcart tbShoppingcart, HttpServletRequest request);

    R<String> deleteShoppingcart(String tbShoppingcartId, HttpServletRequest request);

}
