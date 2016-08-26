package com.fortran.admin.modules.sys.enumeration;

/**
 * <p>性别类型<p>
 * Created by lin on 16/8/26.
 */
public enum GenderType {

    MALE("1","男"),FEMALE("0","女");

    private String value;

    private String label;

    GenderType(String value,String label){
        this.label = label;
        this.value = value;
    }

    public static String getLabelByValue(String value){
        String result = "";
        for (GenderType status : GenderType.values()){
            if (value.equalsIgnoreCase(status.value)){
                result = status.label;
                break;
            }
        }
        return result;
    }
}
