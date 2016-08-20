package com.zcoder.admin.modules.sys.dao;

import com.zcoder.admin.modules.sys.domain.Role;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lin
 * @Date: 2016-08-01 Time: 13:17
 * @description:
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Long> ,JpaSpecificationExecutor<Role> {


    List<Role> findRoles(Role role)throws DataAccessException;

}
