package com.zcoder.admin.modules.gen.dao;

import com.zcoder.admin.modules.gen.domain.Column;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lin
 * @Date: 2016-08-01 Time: 14:00
 * @description:
 */
@Repository
public interface GenDao {

    public List queryTable()throws DataAccessException;

    List<Column> getColumnsByTableName(String tableName)throws DataAccessException;
}
