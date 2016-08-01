package com.fortran.admin.modules.sys.dao;

import com.fortran.admin.modules.core.common.CrudDao;
import com.fortran.admin.modules.core.config.mybatis.annotation.MybatisDao;
import com.fortran.admin.modules.sys.domain.User;
import org.springframework.dao.DataAccessException;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 11:02
 * @description: 用户DAO
 */
@MybatisDao
public interface UserDao extends CrudDao<User> {

    User findByLoginName(String loginName) throws DataAccessException ;

    User findByLoginNameAndLoginPwd(String loginName,String loginPwd) throws DataAccessException ;



}
