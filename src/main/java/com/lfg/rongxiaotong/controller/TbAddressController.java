package com.lfg.rongxiaotong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.TbAddress;
import com.lfg.rongxiaotong.service.TbAddressService;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/address")
public class TbAddressController {
    @Resource
    private TbAddressService tbAddressService;

    @PostMapping("/getAddressPageList")
    private R<List<TbAddress>> getAddressPageList(@RequestBody TbAddress tbAddress, HttpServletRequest request) {
        return tbAddressService.getAddressPageList(tbAddress, request);
    }

    @GetMapping("/getAddressInfo")
    private R<TbAddress> getAddressInfo(@RequestParam String addressId, HttpServletRequest request) {
        if (addressId == null) {
            return R.error("农业知识id不能为空");
        }
        return tbAddressService.getAddressInfo(addressId, request);
    }

    @PostMapping("updateAddress")
    private R<String> updateAddress(@RequestBody TbAddress tbAddress, HttpServletRequest request) {
        if (tbAddress == null) {
            return R.error("农业知识id不能为空");
        }
        return tbAddressService.updateAddress(tbAddress, request);
    }

    @GetMapping("setDefaultAddress")
    private R<String> setDefaultAddress(@RequestParam String defaultAddress, String newAddress, HttpServletRequest request) {
        if (defaultAddress == null || newAddress == null) {
            return R.error("地址不能为空");
        }
        return tbAddressService.setDefaultAddress(defaultAddress, newAddress, request);
    }

    @PostMapping("addAddress")
    private R<String> addAddress(@RequestBody TbAddress tbAddress, HttpServletRequest request) {
        if (tbAddress == null) {
            return R.error("农业知识id不能为空");
        }
        return tbAddressService.addAddress(tbAddress, request);
    }

    @DeleteMapping("deleteAddress")
    private R<String> deleteAddress(@RequestParam String addressId, HttpServletRequest request) {
        if (addressId == null || addressId.isEmpty()) {
            return R.error("农业知识id不能为空");
        }
        return tbAddressService.deleteAddress(addressId, request);
    }
}
