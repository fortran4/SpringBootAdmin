package com.fortran.admin.modules.sys.service;

import com.fortran.admin.modules.core.common.CrudService;
import com.fortran.admin.modules.core.config.mybatis.Page;
import com.fortran.admin.modules.core.config.mybatis.PageHelper;
import com.fortran.admin.modules.core.exception.ServiceException;
import com.fortran.admin.modules.sys.dao.RoleDao;
import com.fortran.admin.modules.sys.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: lin
 * @Date: 2016-08-02 Time: 13:11
 * @description:<p>角色服务层</p>
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class RoleService extends CrudService<RoleDao, Role> {

    /**
     * <p>查询所有角色</p>
     *
     * @param page 分页参数
     * @return
     * @throws ServiceException
     */
    public Page<Role> findRoles(Page<Role> page) throws ServiceException {
        List<Role> data = dao.findRoles(page.getCondition());
        return PageHelper.endPage();
    }

}
