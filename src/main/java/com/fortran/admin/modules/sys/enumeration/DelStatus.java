package com.fortran.admin.modules.sys.enumeration;

/**
 * <p>删除状态<p>
 * Created by lin on 16/8/26.
 */
public enum DelStatus {

    DEL("1","已删除"),NOMAL("0","正常");

    private String value;

    private String label;

    DelStatus(String value,String label){
        this.label = label;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public static String getLabelByValue(String value){
        String result = "";
        for (DelStatus status : DelStatus.values()){
            if (status.value.equalsIgnoreCase(value)){
                result = status.label;
                break;
            }
        }
        return result;
    }
}
