package com.zcoder.admin.modules.core.enumeration;

/**
 * 页面操作类型枚举
 *
 * Created by lin on 16/8/9.
 */
public enum Action {

    EDIT("编辑","edit"),ADD("新增","add"),VIEW("查看","view");

    private String label;
    private String value;

    Action(String label,String value){
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
