package com.lfg.rongxiaotong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lfg.rongxiaotong.domain.TbBankUser;
import org.apache.ibatis.annotations.Mapper;

/**
* @author liufaguang
* @description 针对表【tb_bank_user(银行用户表)】的数据库操作Mapper
* @createDate 2023-10-17 21:16:11
* @Entity com.lfg.rongxiaotong.domain.TbBankUser
*/
@Mapper
public interface TbBankUserMapper extends BaseMapper<TbBankUser> {

}




