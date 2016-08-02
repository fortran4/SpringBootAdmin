package com.fortran.admin.modules.sys.service;

import com.fortran.admin.modules.core.common.CrudService;
import com.fortran.admin.modules.sys.dao.RoleDao;
import com.fortran.admin.modules.sys.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: lin
 * @Date: 2016-08-02 Time: 13:11
 * @description:<p>角色服务层</p>
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class RoleService extends CrudService<RoleDao,Role> {

}
