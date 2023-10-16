package com.lfg.rongxiaotong;

import com.lfg.rongxiaotong.domain.User;
import com.lfg.rongxiaotong.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RongXiaoTongApplicationTests {
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
