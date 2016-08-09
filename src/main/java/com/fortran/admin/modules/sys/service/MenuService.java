package com.fortran.admin.modules.sys.service;

import com.fortran.admin.modules.core.common.CrudService;
import com.fortran.admin.modules.core.config.mybatis.Page;
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
     * @param menu 包含查询条件用户对象
     * @return
     * @throws ServiceException
     */
    public Page<Menu> findUsers(Page<Menu> page, Menu menu) throws ServiceException {
        menu.setPage(page);
        List<Menu> data = dao.findMenus(menu);
        page.setList(data);
        return page;
    }


}
