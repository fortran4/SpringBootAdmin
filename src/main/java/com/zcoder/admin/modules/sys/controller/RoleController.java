package com.zcoder.admin.modules.sys.controller;

import com.google.common.base.Strings;
import com.zcoder.admin.modules.core.common.BaseController;
import com.zcoder.admin.modules.core.exception.ServiceException;
import com.zcoder.admin.modules.core.message.RespMsg;
import com.zcoder.admin.modules.sys.domain.Role;
import com.zcoder.admin.modules.sys.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
     * @param action @See{com.zcoder.admin.modules.core.enumeration.Action}
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
    @RequestMapping(value = "/role/findAllInit")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, Role role) {
        return ROLE_LIST;
    }

    /**
     * <p>条件查询</p>
     *
     * @param role    查询参数
     * @param request 请求对象
     * @return dataTables格式数据
     */
    @RequestMapping(value = "/role/findAll")
    @ResponseBody
    public Map<String, Object> findAll(Role role, HttpServletRequest request) {
        Page<Role> page = roleService.findAll(getPage(request), role);
        return builderDataTable(page, request);
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
