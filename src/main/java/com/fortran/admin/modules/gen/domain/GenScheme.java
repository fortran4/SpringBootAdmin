package com.fortran.admin.modules.gen.domain;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author: lin
 * @Date: 2016-08-01 Time: 13:41
 * @description: 生成方案
 */
public class GenScheme {

    private Gen gen;

    public GenScheme(Gen gen){
        this.gen = gen;
    }

    public Map<String,String> getScheme(){
        Map<String,String> schemes = Maps.newHashMap();
        schemes.put("jsp",gen.getJsp());
        schemes.put("web",gen.getWeb());
        schemes.put("service",gen.getService());
        schemes.put("dao",gen.getDao());
        schemes.put("domain",gen.getDomain());
        return schemes;
    }

}
