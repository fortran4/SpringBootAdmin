package com.fortran.admin.modules.gen.domain;

import com.fortran.admin.modules.core.utils.CamelCaseUtils;
import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: lin
 * @Date: 2016-08-01 Time: 13:25
 * @description: 对应数据库表对象
 */
@Data
public  class Table implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4737050971995397319L;

    /**
     * 对应实体类名称
     */
    private String className;

    /**
     * 包含字段
     */
    private List<Column> columns = Lists.newArrayList();

    /**
     * 注释
     */
    private String comments;

    /**
     * 表名
     */
    private String name;


    public Table(String tableName){
        this.name = tableName;
        this.className = CamelCaseUtils.toCapitalizeCamelCase(tableName);
    }

}
