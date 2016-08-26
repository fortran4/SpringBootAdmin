package com.fortran.admin.modules.sys.enumeration;

/**
 * <p>显示隐藏状态<p>
 * Created by lin on 16/8/26.
 */
public enum ShowStatus {


    SHOW("0","显示"),HIDE("1","隐藏");

    private String value;

    private String label;

    ShowStatus(String value,String label){
        this.label = label;
        this.value = value;
    }

    public static String getLabelByValue(String value){
        String result = "";
        for (ShowStatus status : ShowStatus.values()){
            if (value.equalsIgnoreCase(status.value)){
                result = status.label;
                break;
            }
        }
        return result;
    }
}
