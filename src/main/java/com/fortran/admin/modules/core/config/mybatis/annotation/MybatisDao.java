package com.fortran.admin.modules.core.config.mybatis.annotation;

import org.springframework.stereotype.Component;
import java.lang.annotation.*;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 10:46
 * @description: 标识MyBatis的DAO,方便{@link org.mybatis.spring.mapper.MapperScannerConfigurer}的扫描。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MybatisDao {
}
