package com.lfg.qr_day1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lfg.qr_day1.domain.Student;
import org.apache.ibatis.annotations.Mapper;

/**
* @author liufaguang
* @description 针对表【student】的数据库操作Mapper
* @createDate 2023-09-12 15:41:59
* @Entity com.lfg.qr_day1.domain.Student
*/
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}




