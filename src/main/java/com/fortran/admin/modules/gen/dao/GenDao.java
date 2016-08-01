package com.fortran.admin.modules.gen.dao;

import com.fortran.admin.modules.core.config.mybatis.annotation.MybatisDao;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author: lin
 * @Date: 2016-08-01 Time: 14:00
 * @description:
 */
@MybatisDao
public interface GenDao {

    @Select("show tables")
    public List queryTable()throws DataAccessException;
}
