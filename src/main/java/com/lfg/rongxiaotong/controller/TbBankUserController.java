package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbBankUser;
import com.lfg.rongxiaotong.service.TbBankUserService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/bankUser")
public class TbBankUserController {
    @Resource
    private TbBankUserService tbBankUserService;
    @PostMapping("/getBankUserPageList")
    private R<Page<TbBankUser>> getBankUserPageList(@RequestBody TbBankUser tbBankUser, Integer size, Integer current, HttpServletRequest request){
        return tbBankUserService.getBankUserPageList(tbBankUser, size, current,request);
    }
    @GetMapping("/getBankUserInfo")
    private R<TbBankUser>  getBankUserInfo(@RequestParam String bankUserId, HttpServletRequest request){
        if (bankUserId == null){
            return R.error("农业知识id不能为空");
        }
        return tbBankUserService.getBankUserInfo(bankUserId,request);
    }
    @PostMapping("updateBankUser")
    private R<String>  updateBankUser(@RequestBody TbBankUser tbBankUser,HttpServletRequest request) {
        if (tbBankUser == null){
            return  R.error("农业知识id不能为空");
        }
        return tbBankUserService.updateBankUser(tbBankUser,request);
    }
    @PostMapping("addBankUser")
    private R<String>  addBankUser(@RequestBody TbBankUser tbBankUser,HttpServletRequest request) {
        if (tbBankUser == null){
            return R.error("农业知识id不能为空");
        }
        return tbBankUserService.addBankUser(tbBankUser,request);
    }
    @DeleteMapping("deleteBankUser")
    private R<String>  deleteBankUser(@RequestParam String bankUserId,HttpServletRequest request) {
        if (bankUserId == null || bankUserId.isEmpty()){
            return R.error("农业知识id不能为空");
        }
        return tbBankUserService.deleteBankUser(bankUserId,request);
    }
}
