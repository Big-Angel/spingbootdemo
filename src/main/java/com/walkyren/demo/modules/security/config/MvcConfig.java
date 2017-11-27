package com.walkyren.demo.modules.security.config;

import com.walkyren.demo.modules.security.authorization.interceptor.AdminAuthInterceptor;
import com.walkyren.demo.modules.security.authorization.interceptor.AdminInfoMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: daxian
 * Date: 2017/11/27
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Resource
    private AdminAuthInterceptor adminAuthInterceptor;

    @Resource
    private AdminInfoMethodArgumentResolver adminInfoMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminAuthInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(adminInfoMethodArgumentResolver);
    }
}
