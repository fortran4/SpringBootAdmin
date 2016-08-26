package com.fortran.admin.modules.core.config.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: lin
 * @Date: 2016-08-02 Time: 19:49
 * @description: <p>类型转换配置</p>
 *               <p>自定义实现httpMessageConverter</p>
 */


//@Configuration
@Slf4j
public class ConverterConfig extends WebMvcConfigurerAdapter {

  /* @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = Lists.newArrayList();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(supportedMediaTypes);
        converter.setPrettyPrint(false);
        converter.setObjectMapper(new JsonMapper());
        return converter;
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
        super.configureMessageConverters(converters);
    }*/

}
