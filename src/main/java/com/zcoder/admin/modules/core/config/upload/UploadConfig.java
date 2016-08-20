package com.zcoder.admin.modules.core.config.upload;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 15:03
 * @description: 上传配置
 */
@Configuration
public class UploadConfig {

    @Bean(name = "multipartResolver")
    public CustomCommonsMultipartResolver getCustomCommonsMultipartResolver() throws Exception {
        CustomCommonsMultipartResolver resolver = new CustomCommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setMaxUploadSize(1073741824);
        resolver.setMaxInMemorySize(40960);
        //resolver.setUploadTempDir(new ClassPathResource("tempFiles"));
        return resolver;
    }
}
