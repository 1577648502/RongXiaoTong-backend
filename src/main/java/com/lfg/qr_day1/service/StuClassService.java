package com.lfg.qr_day1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.qr_day1.domain.StuClass;
import com.lfg.qr_day1.domain.beans.StuClassBean;

/**
* @author liufaguang
* @description 针对表【stu_class(班级)】的数据库操作Service
* @createDate 2023-09-15 11:12:55
*/
public interface StuClassService extends IService<StuClass> {
    StuClassBean getBeanAllInfo(Integer stuClassId);

}
