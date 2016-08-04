package com.fortran.admin.modules.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: lin
 * @Date: 2016-08-04 Time: 9:40
 * @description: <p>应用级别的基本配置</p>
 */
@Configuration
@Slf4j
public class WebAppConfig extends WebMvcConfigurerAdapter {


    /**
     * <p>加入自定义拦截器</p>
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //加入自定义拦截器
        super.addInterceptors(registry);
    }
}
