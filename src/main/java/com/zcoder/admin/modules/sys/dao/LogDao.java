package com.zcoder.admin.modules.sys.dao;

import com.zcoder.admin.modules.sys.domain.Log;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author: lin
 * @Date: 2016-08-04 Time: 9:48
 * @description:
 */
@Repository
public interface LogDao extends JpaRepository<Log, Long>,JpaSpecificationExecutor<Log> {


    @Query("select L from Log L where l.type = ?1 ")
    Page<Log> findByLogType(String type, Pageable pageable) throws DataAccessException;

}
