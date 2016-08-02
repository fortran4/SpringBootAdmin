package com.fortran.admin.modules.sys.service;

import com.fortran.admin.modules.core.common.CrudService;
import com.fortran.admin.modules.sys.dao.MenuDao;
import com.fortran.admin.modules.sys.domain.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: lin
 * @Date: 2016-08-02 Time: 13:13
 * @description:<p>菜单服务层</p>
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class MenuService extends CrudService<MenuDao,Menu> {


}
