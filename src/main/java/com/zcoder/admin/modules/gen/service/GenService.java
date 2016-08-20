package com.zcoder.admin.modules.gen.service;

import com.zcoder.admin.modules.gen.dao.GenDao;
import com.zcoder.admin.modules.gen.domain.Column;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author: lin
 * @Date: 2016-08-01 Time: 13:53
 * @description: 代码生成服务层
 */
@Service
@Slf4j
public class GenService {

    @Autowired
    private GenDao genDao;

    /**
     * 查询所有表
     * @return
     */
    public List queryTable(){
        return genDao.queryTable();
        }

    /**
     * 根据表名查询字段
     * @param tableName
     * @return
     */
    public List<Column> getColumnsByTableName(String tableName) {
        return genDao.getColumnsByTableName(tableName);
    }

}
