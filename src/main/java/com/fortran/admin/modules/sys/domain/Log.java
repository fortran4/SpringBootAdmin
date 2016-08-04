package com.fortran.admin.modules.sys.domain;

import com.fortran.admin.modules.core.common.Domain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author: lin
 * @Date: 2016-08-02 Time: 9:22
 * @description: <p>系统日志</p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Log extends Domain{

    /**
     * 主键
     */
    private Long logId;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 日志类型
     */
    private String type;
    /**
     * 请求地址
     */
    private String remoteAddr;
    /**
     * 请求终端
     */
    private String userAgent;
    /**
     * 请求链接
     */
    private String requestUri;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String  params;
    /**
     * 请求响应信息
     */
    private String response;
    /**
     * 异常信息
     */
    private String exception;

}
