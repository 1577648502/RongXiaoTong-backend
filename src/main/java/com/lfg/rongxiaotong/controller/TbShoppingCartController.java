package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbShoppingcart;
import com.lfg.rongxiaotong.service.TbShoppingcartService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/shoppingCart")
public class TbShoppingCartController {
    @Resource
    private TbShoppingcartService tbShoppingCartService;
    @RequestMapping("/getShoppingCartPageList")
    private R<Page<TbShoppingcart>> getShoppingCartPageList(@RequestBody TbShoppingcart tbShoppingCart, Integer size, Integer current, HttpServletRequest request){
        return tbShoppingCartService.getShoppingcartPageList(tbShoppingCart, size, current,request);
    }
    @RequestMapping("/getShoppingCartInfo")
    private R<TbShoppingcart>  getShoppingCartInfo(@RequestParam String shoppingCartId, HttpServletRequest request){
        if (shoppingCartId == null){
            return R.error("农业知识id不能为空");
        }
        return tbShoppingCartService.getShoppingcartInfo(shoppingCartId,request);
    }
    @RequestMapping("updateShoppingCart")
    private R<String>  updateShoppingCart(@RequestBody TbShoppingcart tbShoppingCart,HttpServletRequest request) {
        if (tbShoppingCart == null){
            return  R.error("农业知识id不能为空");
        }
        return tbShoppingCartService.updateShoppingcart(tbShoppingCart,request);
    }
    @RequestMapping("addShoppingCart")
    private R<String>  addShoppingCart(@RequestBody TbShoppingcart tbShoppingCart,HttpServletRequest request) {
        if (tbShoppingCart == null){
            return R.error("农业知识id不能为空");
        }
        return tbShoppingCartService.addShoppingcart(tbShoppingCart,request);
    }
    @RequestMapping("deleteShoppingCart")
    private R<String>  deleteShoppingCart(@RequestParam String shoppingCartId,HttpServletRequest request) {
        if (shoppingCartId == null || shoppingCartId.isEmpty()){
            return R.error("农业知识id不能为空");
        }
        return tbShoppingCartService.deleteShoppingcart(shoppingCartId,request);
    }
}
