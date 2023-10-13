package com.lfg.rongxiaotong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.rongxiaotong.domain.TbQuestion;
import com.lfg.rongxiaotong.domain.TbQuestion;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.TbQuestionService;
import com.lfg.rongxiaotong.mapper.TbQuestionMapper;
import com.lfg.rongxiaotong.utius.IsAdmin;
import com.lfg.rongxiaotong.utius.R;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
* @author liufaguang
* @description 针对表【tb_question(在线问答)】的数据库操作Service实现
* @createDate 2023-10-13 11:34:18
*/
@Service
public class TbQuestionServiceImpl extends ServiceImpl<TbQuestionMapper, TbQuestion>
    implements TbQuestionService{
    @Override
    public R<Page<TbQuestion>> getQuestionPageList(TbQuestion tbQuestion, Integer size, Integer current, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == size || null == current) {
                return R.error("参数错误");
            }
            Page<TbQuestion> page = new Page<>(current, size);
            LambdaQueryWrapper<TbQuestion> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(null != tbQuestion.getTitle(), TbQuestion::getTitle, tbQuestion.getTitle());
            Page<TbQuestion> tbQuestionPage = this.page(page, wrapper);
            return R.success(tbQuestionPage);
        }
        return  R.error("未登录");

    }


    @Override
    public R<TbQuestion> getQuestionInfo(String questionId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == questionId) {
                return R.error("参数错误");
            }
            return R.success(this.getById(questionId));
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> updateQuestion(TbQuestion tbQuestion, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbQuestion ) {
                return R.error("参数错误");
            }
            boolean saved = this.updateById(tbQuestion);
            if (saved) {
                return R.success("更新成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> addQuestion(TbQuestion tbQuestion, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("data");
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == tbQuestion ) {
                return R.error("参数错误");
            }
            boolean saved = this.save(tbQuestion);
            if (saved) {
                return R.success("添加成功");
            }
        }
        return  R.error("未登录");
    }

    @Override
    public R<String> deleteQuestion(String questionId, HttpServletRequest request) {
        String admin = IsAdmin.isAdmin(request);
        if (!admin.equals("未登录")) {
            if (null == questionId || questionId.isEmpty()) {
                return R.error("参数错误");
            }
            boolean saved = this.removeById(questionId);
            if (saved) {
                return R.success("删除成功");
            }
        }
        return  R.error("未登录");
    }
}




