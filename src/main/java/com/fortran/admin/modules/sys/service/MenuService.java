package com.fortran.admin.modules.sys.service;

import com.fortran.admin.modules.core.common.CrudService;
import com.fortran.admin.modules.core.config.mybatis.Page;
import com.fortran.admin.modules.core.config.mybatis.PageHelper;
import com.fortran.admin.modules.core.exception.ServiceException;
import com.fortran.admin.modules.sys.dao.MenuDao;
import com.fortran.admin.modules.sys.domain.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: lin
 * @Date: 2016-08-02 Time: 13:13
 * @description:<p>菜单服务层</p>
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class MenuService extends CrudService<MenuDao, Menu> {


    /**
     * <p>查询所有菜单</p>
     *
     * @param page 分页参数
     * @return
     * @throws ServiceException
     */
    public Page<Menu> findMenus(Page<Menu> page) throws ServiceException {
        List<Menu> data = dao.findMenus(page.getCondition());
        return PageHelper.endPage();
    }


}
