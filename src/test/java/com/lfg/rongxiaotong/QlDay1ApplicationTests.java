package com.lfg.rongxiaotong;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.UserService;
import com.lfg.rongxiaotong.utius.R;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootTest
class QlDay1ApplicationTests {
    @Resource
    private UserService userService;
  @Test
  public  void register(){
      for (int i = 0; i < 200; i++) {
          User user = new User();
          user.setUserName("test"+i);
          user.setPassword("test"+i);
          user.setRole("test");
          userService.register(user);
      }

  }
}
