package com.lfg.qr_day1.mapper;

import com.lfg.qr_day1.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author liufaguang
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-09-18 15:54:07
* @Entity com.lfg.qr_day1.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




