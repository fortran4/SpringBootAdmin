package com.fortran.admin.modules.sys.controller;

import com.fortran.admin.modules.core.common.BaseController;
import com.fortran.admin.modules.sys.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>菜单控制器</p>
 * Created by lin on 16/8/9.
 */
@Controller
@Slf4j
public class MenuController extends BaseController{

    @Autowired
    private MenuService menuService;

    private final String MENU_FORM = "modules/sys/menuForm";

    private final String MENU_LIST = "modules/sys/menuList";


}
