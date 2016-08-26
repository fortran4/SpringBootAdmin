package com.fortran.admin.modules.sys.controller;

import com.fortran.admin.modules.core.common.BaseController;
import com.fortran.admin.modules.core.config.mybatis.Page;
import com.fortran.admin.modules.core.exception.ServiceException;
import com.fortran.admin.modules.core.message.RespMsg;
import com.fortran.admin.modules.sys.domain.Menu;
import com.fortran.admin.modules.sys.service.MenuService;
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
 * <p>菜单控制器</p>
 * Created by lin on 16/8/9.
 */
@Controller
@Slf4j
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    private final String MENU_FORM = "modules/sys/menuForm";

    private final String MENU_LIST = "modules/sys/menuList";


    /**
     * 新增编辑
     *
     * @param menu
     * @param model
     * @param action @See{com.fortran.admin.modules.core.enumeration.Action}
     * @returnR
     */
    @RequestMapping(value = "/menu/form/{action}")
    public String form(Model model, @PathVariable String action, Menu menu) {
        if (!Strings.isNullOrEmpty(action)) model.addAttribute("action", action);
        model.addAttribute("menu", menu);
        return MENU_FORM;
    }


    /**
     * 查询列表
     *
     * @param menu
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/menu/findAll")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, Menu menu) {
        Page<Menu> page = menuService.findMenus(Page.getInstance(request,menu));
        model.addAttribute("page",page);
        return MENU_LIST;
    }


    /**
     * 删除
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu/delete/{id}")
    public RespMsg delete(Model model, @PathVariable String id) {

        Menu menu = new Menu();
        try {
            menu.setMenuId(Long.parseLong(id));
            menuService.delete(menu);
        } catch (ServiceException e) {
            return error(menu);
        }
        return ok(menu);

    }
}
