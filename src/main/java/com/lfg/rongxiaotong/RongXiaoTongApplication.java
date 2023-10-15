package com.lfg.rongxiaotong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class RongXiaoTongApplication {

    public static void main(String[] args) {
        SpringApplication.run(RongXiaoTongApplication.class, args);
    }

}
