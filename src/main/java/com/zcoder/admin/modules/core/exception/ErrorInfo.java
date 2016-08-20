package com.zcoder.admin.modules.core.exception;

import lombok.Data;

/**
 * <p>全局错误信息</p>
 * Created by lin on 16/8/9.
 */
@Data
public class ErrorInfo<T> {

    public static final Integer OK = 200;
    public static final Integer ERROR = 500;

    private Integer code;
    private String message;
    private String url;
    private T data;
}
