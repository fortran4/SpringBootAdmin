package com.fortran.admin.modules.core.config.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 10:20
 * @description: ehcache config
 */
@Configuration
@EnableCaching
public class CacheConfig {


    @Bean(name = "cacheManager")
    public net.sf.ehcache.CacheManager getCacheManager(EhCacheManagerFactoryBean bean){
        return bean.getObject();

    }

    /*
     * 据shared与否的设置,Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("cfg/ehcache.xml"));
        cacheManagerFactoryBean.setShared (true);
        return cacheManagerFactoryBean;
    }


}
