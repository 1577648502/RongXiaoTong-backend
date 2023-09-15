package com.lfg.qr_day1.service;

import com.lfg.qr_day1.domain.TestGlhkDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.qr_day1.domain.beans.TestGlhkDeptBean;

import java.util.List;

/**
* @author liufaguang
* @description 针对表【test_glhk_dept】的数据库操作Service
* @createDate 2023-09-15 14:45:12
*/
public interface TestGlhkDeptService extends IService<TestGlhkDept> {

    List<TestGlhkDeptBean> selectAll();

}
