package com.fortran.admin.modules.core.message;

import lombok.Data;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 13:31
 * @description: 响应AJAX请求返回对象，通常返回JSON格式
 */
@Data
public class RespMsg <T>{

    private int code = RespMsgStatus.OK;

    private String msg;

    private T data;

    public RespMsg(){

    }

    public RespMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespMsg(String msg,T data) {
        this.msg = msg;
        this.data = data;
    }

    public RespMsg(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "RespMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
