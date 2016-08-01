package com.fortran.admin.modules.gen.domain;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author: lin
 * @Date: 2016-08-01 Time: 13:44
 * @description: 模板对象
 */
@XmlRootElement(name = "template")
@Data
public class GenTemplate {

    private String name;    // 名称
    private String filePath;        // 生成文件路径
    private String fileName;        // 文件名
    private String content;        // 内容


}