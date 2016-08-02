package com.fortran.admin.modules.sys.dao;

import com.fortran.admin.modules.core.common.CrudDao;
import com.fortran.admin.modules.core.config.mybatis.annotation.MybatisDao;
import com.fortran.admin.modules.sys.domain.Menu;

/**
 * @author: lin
 * @Date: 2016-08-02 Time: 13:10
 * @description: <p>系统菜单</p>
 */
@MybatisDao
public interface MenuDao  extends CrudDao<Menu> {


}

