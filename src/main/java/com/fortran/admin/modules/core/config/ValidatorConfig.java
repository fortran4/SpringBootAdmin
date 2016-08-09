package com.fortran.admin.modules.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

/**
 * <P>bean validator</P>
 * Created by lin on 16/8/8.
 */
@Configuration
public class ValidatorConfig {

    @Bean
    public Validator validator(){
        return new LocalValidatorFactoryBean();
    }

}
