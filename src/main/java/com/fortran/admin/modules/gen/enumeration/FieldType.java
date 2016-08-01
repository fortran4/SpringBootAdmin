package com.fortran.admin.modules.gen.enumeration;

/**
 * @author: lin
 * @Date: 2016-08-01 Time: 13:30
 * @description: JSP中字段显示的类型
 */
public enum FieldType {

    INPUT("单行文本", "text"), TEXTAREA("多行文本", "textarea"), SELECT("下拉列表", "select"),

    RADIOBOX("单选", "radiobox"), CHECKBOX("复选框", "checkbox"),

    DATESELECT("日期", "dateSelect");

    private String label;

    private String value;


    FieldType(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

}
