package com.lfg.rongxiaotong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringbootConfigure implements WebMvcConfigurer {
    //@Value可以将配置文件的内容自动注入到属性内
    @Value("${SavePath.ProfilePhoto}")
    private String ProfilePhotoPath;   //图标物理存储路径
    @Value("${SavePath.ProfilePhotoMapper}")
    private String ProfilePhotoMapperPath;   //图标映射路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(ProfilePhotoMapperPath+"**").addResourceLocations("file:"+ProfilePhotoPath);
    }
}