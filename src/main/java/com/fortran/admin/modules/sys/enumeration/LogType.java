package com.fortran.admin.modules.sys.enumeration;

/**
 * Created by lin on 16/8/23.
 */
public enum LogType {

    OPERATION_LOG("1","操作日志"),EXCEPTION_LOG("2","异常日志");

    private String value;

    private String label;

    LogType(String value,String label){
        this.label = label;
        this.value = value;
    }

    public static String getLabelByValue(String value){
        String result = "";
        for (LogType logType : LogType.values()){
                if (value.equalsIgnoreCase(logType.value)){
                    result = logType.label;
                    break;
                }
        }
        return result;
    }
}
