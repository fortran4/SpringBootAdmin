package com.fortran.admin.modules.core.config.mybatis;

import com.fortran.admin.modules.core.config.mybatis.annotation.MybatisDao;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 10:29
 * @description: mybatis 配置
 */
@Configuration
//@ConditionalOnClass({ SqlSessionFactory.class, SqlSessionFactoryBean.class })
//@ConditionalOnBean(DataSource.class)
//@AutoConfigureAfter(DruidConfig.class)//数据源创建后加载
@MapperScan(basePackages = {"com.fortran.admin"},annotationClass = MybatisDao.class)
public class MybatisConfig {

    private final Logger log = LoggerFactory.getLogger(MybatisConfig.class);

   /* @Autowired
    private MybatisAutoConfiguration properties;



    @Bean(name = "sqlSessionFactory")
    @ConditionalOnMissingBean
    public SqlSessionFactory getSqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        try {
            factory.setDataSource(dataSource);
            if (Strings.isNullOrEmpty(this.properties.getConfig())) {
                throw new NullPointerException("load mybatis.config error.please check application.properties ");
            }
            if (log.isDebugEnabled()){
                log.debug("load mybatis configLocation : {}", this.properties.getConfig());
                log.debug("load mybatis typeAliasesPackage : {}", this.properties.getTypeAliasesPackage());
                log.debug("load mybatis typeHandlersPackage : {}", this.properties.getTypeHandlersPackage());
                log.debug("load mybatis mapperLocations : {}", this.properties.getMapperLocations());
            }
            factory.setConfigLocation(new ClassPathResource(this.properties.getConfig()));
            factory.setMapperLocations(this.properties.getMapperLocations());
            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        } catch (Exception e) {
            log.error("create sqlSesssionFactory error,{}", e.getMessage());
        }
        return factory.getObject();
    }*/

    /*@Bean
    @ConditionalOnMissingBean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory,  this.properties.getExecutorType());
    }*/


/*

   @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.fortran.admin");
        mapperScannerConfigurer.setAnnotationClass(MybatisDao.class);
        return mapperScannerConfigurer;
    }
*/

/*
    @Bean
    @ConditionalOnMissingBean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/
}
