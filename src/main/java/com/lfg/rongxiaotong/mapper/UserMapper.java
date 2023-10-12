package com.lfg.rongxiaotong.mapper;

import com.lfg.rongxiaotong.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author liufaguang
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-09-27 17:35:55
* @Entity com.lfg.rongxiaotong.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




