package com.zcoder.admin.modules.gen;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lin
 * @Date: 2016-08-04 Time: 16:51
 * @description: <p>代码生成工具，命令行入口</p>
 */
@Slf4j
public class GenMain {

    /**
     * 可用的模板名称
     */
    private static final String [] ENABLE_TEMPLATE = {"web","jsp","service","dao","domain","mapper"};


    /**
     * <p>1.args.length==0,默认生成所有</p>
     * <p>2.args的值包括[web,jsp,service,dao,domain,mapper]</p>
     *
     * @param args
     */
    public static void main(String[] args) {

        log.info("--------------------Gen code Console-------------------------");
        //log.info("请选择序号，生成对应模板的代码: 0:所有 ; 1.web ; 2.jsp ; 3.service ; 4.dao ;5.domain ; 6.mapper ;");
        if (args.length == 0) {
            genAll();
        } else {
            genByTemplateName(args);
        }
        log.info("--------------------Gen code end-------------------------");

    }

    /**
     * 生成所有
     */
    private static void genAll() {

    }

    /**
     * 根据模板名称生成
     * @param templates 模板编号
     */
    private static void genByTemplateName(String[] templates) {



    }

}
