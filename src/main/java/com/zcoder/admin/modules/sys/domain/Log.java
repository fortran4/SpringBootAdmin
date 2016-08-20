package com.zcoder.admin.modules.sys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zcoder.admin.modules.core.common.BaseEntity;
import com.zcoder.admin.modules.sys.enumeration.LogType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: lin
 * @Date: 2016-08-02 Time: 9:22
 * @description: <p>系统日志</p>
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Log extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId; //日志ID

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @Column
    private Date createDate; //创建时间

    @Column
    private String type; //日志类型

    @Column
    private String remoteAddr; //请求地址

    @Column
    private String userAgent;//请求终端

    @Column
    private String requestUri; //请求地址

    @Column
    private String method; //请求方法

    @Column
    private String params;//请求参数

    @Column
    private String response;//请求响应信息

    @Column
    private String exception;// 异常信息

    @Column
    public String getType() {
        return LogType.getLabelByValue(this.type);
    }

}
