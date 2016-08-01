package com.fortran.admin.modules.gen.domain;

import com.fortran.admin.modules.gen.enumeration.FieldType;
import com.fortran.admin.modules.gen.enumeration.QueryMode;
import lombok.Data;
import java.io.Serializable;

/**
 * @author: lin
 * @Date: 2016-08-01 Time: 13:27
 * @description:  数据库表字段
 */
@Data
public class Column implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8106047230785828976L;


    /**
     * 字段名
     */
    private String field;

    /**
     * 字段长度
     */
    private String length;

    /**
     * 数据库字段类型
     */
    private String jdbcType;

    /**
     * 对应字段类型
     */
    private String javaType;

    /**
     * 是否NULL
     */
    private String isNull;

    /**
     * 是否key
     */
    private String isKey;

    /**
     * 默认值
     */
    private String defaultVal;

    /**
     * 注解
     */
    private String comment;

    /**
     * Java字段名
     */
    private String javaField;



    //-------------------ext-----------------------

    /**
     * 是否是查询条件
     */
    private boolean isQuery;

    /**
     * 是否是查询列表字段
     */
    private boolean isList = true;

    /**
     * 查询方式,默认等于
     */
    private String queryMethod = QueryMode.EQUAL.getValue();


    /**
     * 表单类型，默认text
     */
    private String formType = FieldType.INPUT.getValue();

    /**
     * 表弟校验规则
     */
   // private Validator validator;

    /**
     * 数据字典类型
     */
    private String dictType;

}
