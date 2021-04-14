package com.example.demo.Config;

import com.example.demo.interceptor.Webinterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Webinterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/user/login","/register",
                        "/user/register","/user/reset","/forgotpassword");



    }




}
