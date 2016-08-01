package com.fortran.admin.modules.sys.dao;

import com.fortran.admin.modules.core.common.CrudDao;
import com.fortran.admin.modules.core.config.mybatis.annotation.MybatisDao;
import com.fortran.admin.modules.sys.domain.Role;

/**
 * @author: lin
 * @Date: 2016-08-01 Time: 13:17
 * @description:
 */
@MybatisDao
public interface RoleDao extends CrudDao<Role> {


}
