package com.lfg.qr_day1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 添加jwt拦截器，并指定拦截路径
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(jwtInterceptor())
                .excludePathPatterns("/user/login")             //添加不拦截的请求路径
                .excludePathPatterns("/api/loginBackend")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/swagger-resources/configuration/ui")
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/v2/api-docs-ext")
                .excludePathPatterns("/webjars/*")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/doc.html")
                .addPathPatterns("/**");                  //添加需要拦截的路径
    }

    /**
     * jwt拦截器
     * */
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}

