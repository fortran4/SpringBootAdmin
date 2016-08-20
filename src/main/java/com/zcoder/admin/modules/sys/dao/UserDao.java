package com.zcoder.admin.modules.sys.dao;

import com.zcoder.admin.modules.sys.domain.Menu;
import com.zcoder.admin.modules.sys.domain.Role;
import com.zcoder.admin.modules.sys.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 11:02
 * @description: 用户DAO
 */
@Repository
public interface UserDao extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {


    User findByLoginName(String loginName) throws DataAccessException;


    User findByLoginNameAndLoginPwd(String loginName,String loginPwd) throws DataAccessException ;


    List<Role> findRolesByLoginName(String loginName)throws DataAccessException;

    List<Menu> findPermissionByLoginName(String loginName) throws DataAccessException;


    List<Menu> findMenuByParentId(String parentId)throws DataAccessException;

    List<User> findUsers(User user)throws  DataAccessException;
}
