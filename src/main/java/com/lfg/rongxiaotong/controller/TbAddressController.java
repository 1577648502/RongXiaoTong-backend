package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbAddress;
import com.lfg.rongxiaotong.service.TbAddressService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/address")
public class TbAddressController {
    @Resource
    private TbAddressService tbAddressService;

    @RequestMapping("/getAddressPageList")
    private R<Page<TbAddress>> getAddressPageList(@RequestBody TbAddress tbAddress, Integer size, Integer current, HttpServletRequest request) {
        return tbAddressService.getAddressPageList(tbAddress, size, current, request);
    }

    @RequestMapping("/getAddressInfo")
    private R<TbAddress> getAddressInfo(@RequestParam String addressId, HttpServletRequest request) {
        if (addressId == null) {
            return R.error("农业知识id不能为空");
        }
        return tbAddressService.getAddressInfo(addressId, request);
    }

    @RequestMapping("updateAddress")
    private R<String> updateAddress(@RequestBody TbAddress tbAddress, HttpServletRequest request) {
        if (tbAddress == null) {
            return R.error("农业知识id不能为空");
        }
        return tbAddressService.updateAddress(tbAddress, request);
    }

    @RequestMapping("setDefaultAddress")
    private R<String> setDefaultAddress(@RequestParam String defaultAddress, String newAddress, HttpServletRequest request) {
        if (defaultAddress == null || newAddress == null) {
            return R.error("地址不能为空");
        }
        return tbAddressService.setDefaultAddress(defaultAddress, newAddress, request);
    }

    @RequestMapping("addAddress")
    private R<String> addAddress(@RequestBody TbAddress tbAddress, HttpServletRequest request) {
        if (tbAddress == null) {
            return R.error("农业知识id不能为空");
        }
        return tbAddressService.addAddress(tbAddress, request);
    }

    @RequestMapping("deleteAddress")
    private R<String> deleteAddress(@RequestParam String addressId, HttpServletRequest request) {
        if (addressId == null || addressId.isEmpty()) {
            return R.error("农业知识id不能为空");
        }
        return tbAddressService.deleteAddress(addressId, request);
    }
}
