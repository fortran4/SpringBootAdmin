package com.fortran.admin.modules.sys.service;

import com.fortran.admin.modules.core.common.CrudService;
import com.fortran.admin.modules.core.config.mybatis.Page;
import com.fortran.admin.modules.core.config.mybatis.PageHelper;
import com.fortran.admin.modules.core.exception.ServiceException;
import com.fortran.admin.modules.sys.dao.UserDao;
import com.fortran.admin.modules.sys.domain.Menu;
import com.fortran.admin.modules.sys.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 11:28
 * @description:
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class UserService extends CrudService<UserDao,User> {


    /**
     * <p>根据登录名查询系统菜单权限</p>
     * @param loginName 登录名
     * @return
     * @throws ServiceException
     */
    public  List<Menu> findPermissionByLoginName(String loginName) throws ServiceException{
        return dao.findPermissionByLoginName(loginName);
    }

    /**
     * <p>查询子菜单</p>
     * @param parentId 父ID
     * @return
     * @throws ServiceException
     */
    public List<Menu> findMenuByParentId(String parentId)throws ServiceException{
        return dao.findMenuByParentId(parentId);
    }


    /**
     * <p>查询所有用户</p>
     * @param page 分页参数
     * @return
     * @throws ServiceException
     */
    public Page<User> findUsers(Page<User> page)throws ServiceException{
        List<User> data = dao.findUsers(page.getCondition());
        return PageHelper.endPage();
    }


}
