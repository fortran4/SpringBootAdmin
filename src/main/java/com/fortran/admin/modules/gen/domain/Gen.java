package com.fortran.admin.modules.gen.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author: lin
 * @Date: 2016-08-01 Time: 13:35
 * @description: 生成代码对象
 */
@Data
@Component
@ConfigurationProperties(prefix = "gen", locations = "classpath:gen/gen.properties")
public class Gen {
    /**
     * 类名
     */
    @NotNull(message = "className can not be null")
    private String className;
    /**
     * 表名
     */
    @NotNull(message = "tableName can not be null")
    private String tableName;
    /**
     * 包名
     */
    @NotNull(message = "packageName can not be null")
    private String packageName;
    /**
     * 模块名
     */
    @NotNull(message = "moduleName can not be null")
    private String moduleName;
    /**
     * 子模块名
     */
    private String subModuleName;
    /**
     * 作者
     */
    private String author;
    /**
     * 版本
     */
    private String version;
    /**
     * 功能名（类的注解）
     */
    private String funcName;
    /**
     * 生成策略名
     */
    @NotNull(message = "scheme can not be null")
    private String scheme;
    /**
     * 是否替换已存在的文件
     */
    private String isReplace;
    /**
     * jsp
     */
    private String jsp;
    /**
     * controller
     */
    private String web;
    /**
     * service
     */
    private String service;
    /**
     * dao
     */
    private String dao;
    /**
     * 实体
     */
    private String domain;
}
