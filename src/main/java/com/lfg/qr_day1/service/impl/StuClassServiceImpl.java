package com.lfg.qr_day1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.qr_day1.domain.StuClass;
import com.lfg.qr_day1.domain.beans.StuClassBean;
import com.lfg.qr_day1.mapper.StuClassMapper;
import com.lfg.qr_day1.service.StuClassService;
import org.springframework.stereotype.Service;

/**
 * @author liufaguang
 * @description 针对表【stu_class(班级)】的数据库操作Service实现
 * @createDate 2023-09-15 11:43:23
 */
@Service
public class StuClassServiceImpl extends ServiceImpl<StuClassMapper, StuClass>
        implements StuClassService {

    @Override
    public StuClassBean getBeanAllInfo(Integer stuClassId) {
        return null;
    }
}




