package com.zcoder.admin.modules.sys.controller;

import com.google.common.base.Strings;
import com.zcoder.admin.modules.core.common.BaseController;
import com.zcoder.admin.modules.core.common.constant.Constants;
import com.zcoder.admin.modules.core.exception.ServiceException;
import com.zcoder.admin.modules.core.message.RespMsg;
import com.zcoder.admin.modules.sys.domain.Log;
import com.zcoder.admin.modules.sys.domain.Menu;
import com.zcoder.admin.modules.sys.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
     * @param action @See{com.zcoder.admin.modules.core.enumeration.Action}
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
    @RequestMapping(value = "/menu/findAllInit")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, Menu menu) {
        return MENU_LIST;
    }

    /**
     * <p>日志条件查询</p>
     *
     * @param log     查询参数
     * @param request 请求对象
     * @return dataTables格式数据
     */
    @RequestMapping(value = "/menu/findAll")
    @ResponseBody
    public Map<String, Object> findAll(Menu menu, HttpServletRequest request) {
        Page<Menu> page = menuService.findAll(getPage(request), menu);
        return builderDataTable(page, request);
    }

    /**
     * 删除
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu/delete/{id}")
    public RespMsg delete(@PathVariable String id,Model model) {

        Menu menu = new Menu();
        try {
            menu.setMenuId(Long.parseLong(id));
            menuService.delete(menu);
        } catch (ServiceException e) {
            return error(menu);
        }
        return ok(menu);

    }

    /**
     * 新增或修改
     *
     * @param menu
     * @param model
     * @return
     */
    @RequestMapping(value = "/menu/save/{action}", method = RequestMethod.POST)
    public String saveOrUpdate(@PathVariable String action,Menu menu, Model model) {

        if (!beanValidator(model, menu)) {
            return form(model, action, menu);
        }
        try {
            //menuService.saveOrUpdate(menu, menu != null ? menu.getMenuId() : null);
            rtnMessage(model, Constants.MSG_TYPE_SUCCESS);
        } catch (ServiceException e) {
            rtnMessage(model, Constants.MSG_TYPE_DANGER);
        }

        return MENU_LIST;
    }
}
