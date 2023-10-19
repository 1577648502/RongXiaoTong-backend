package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbShoppingcart;
import com.lfg.rongxiaotong.service.TbShoppingcartService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class TbShoppingCartController {
    @Resource
    private TbShoppingcartService tbShoppingCartService;
    @PostMapping("/getShoppingCartPageList")
    private R<List<TbShoppingcart>> getShoppingCartPageList(@RequestBody TbShoppingcart tbShoppingCart, HttpServletRequest request){
        return tbShoppingCartService.getShoppingcartPageList(tbShoppingCart,request);
    }
    @GetMapping("/getShoppingCartInfo")
    private R<TbShoppingcart>  getShoppingCartInfo(@RequestParam String shoppingCartId, HttpServletRequest request){
        if (shoppingCartId == null){
            return R.error("农业知识id不能为空");
        }
        return tbShoppingCartService.getShoppingcartInfo(shoppingCartId,request);
    }
    @PostMapping("updateShoppingCart")
    private R<String>  updateShoppingCart(@RequestBody TbShoppingcart tbShoppingCart,HttpServletRequest request) {
        if (tbShoppingCart == null){
            return  R.error("农业知识id不能为空");
        }
        return tbShoppingCartService.updateShoppingcart(tbShoppingCart,request);
    }
    @PostMapping("addShoppingCart")
    private R<String>  addShoppingCart(@RequestBody TbShoppingcart tbShoppingCart,HttpServletRequest request) {
        if (tbShoppingCart == null){
            return R.error("农业知识id不能为空");
        }
        return tbShoppingCartService.addShoppingcart(tbShoppingCart,request);
    }
    @DeleteMapping("deleteShoppingCart")
    private R<String>  deleteShoppingCart(@RequestBody ArrayList<String> shoppingCartIds, HttpServletRequest request) {
        if (shoppingCartIds == null){
            return R.error("农业知识id不能为空");
        }
        return tbShoppingCartService.deleteShoppingcart(shoppingCartIds,request);
    }
}
