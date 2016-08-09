package com.fortran.admin.modules.sys.controller;

import com.fortran.admin.modules.core.common.BaseController;
import com.fortran.admin.modules.sys.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>角色控制器</p>
 * Created by lin on 16/8/9.
 */
@Controller
@Slf4j
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;

    private final String ROLE_FORM = "modules/sys/roleForm";

    private final String ROLE_LIST = "modules/sys/roleList";






}
