package com.fortran.admin.modules.sys.controller;

import com.fortran.admin.modules.core.common.BaseController;
import com.fortran.admin.modules.core.config.mybatis.Page;
import com.fortran.admin.modules.core.exception.ServiceException;
import com.fortran.admin.modules.core.message.RespMsg;
import com.fortran.admin.modules.sys.domain.Role;
import com.fortran.admin.modules.sys.service.RoleService;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>角色控制器</p>
 * Created by lin on 16/8/9.
 */
@Controller
@Slf4j
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    private final String ROLE_FORM = "modules/sys/roleForm";

    private final String ROLE_LIST = "modules/sys/roleList";


    /**
     * 新增编辑
     *
     * @param role
     * @param model
     * @param action @See{com.fortran.admin.modules.core.enumeration.Action}
     * @returnR
     */
    @RequestMapping(value = "/role/form/{action}")
    public String form(Model model, @PathVariable String action, Role role) {
        if (!Strings.isNullOrEmpty(action)) model.addAttribute("action", action);
        model.addAttribute("role", role);
        return ROLE_FORM;
    }


    /**
     * 查询列表
     *
     * @param role
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/role/findAll")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, Role role) {
        Page<Role> page = roleService.findRoles(Page.getInstance(request,role));
        model.addAttribute("page",page);
        return ROLE_LIST;
    }


    /**
     * 删除
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/role/delete/{id}")
    public RespMsg delete(Model model, @PathVariable String id) {

        Role role = new Role();
        try {
            role.setRoleId(Long.parseLong(id));
            roleService.delete(role);
        } catch (ServiceException e) {
            return error(role);
        }
        return ok(role);

    }


}
