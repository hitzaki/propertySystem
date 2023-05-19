package com.chen.property.config;

import com.chen.property.interceptor.AdminInterceptor;
import com.chen.property.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/**");
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/user/**");
    }
}
