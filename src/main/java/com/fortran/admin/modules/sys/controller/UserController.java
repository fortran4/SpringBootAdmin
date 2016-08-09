package com.fortran.admin.modules.sys.controller;

import com.fortran.admin.modules.core.common.BaseController;
import com.fortran.admin.modules.core.common.constant.Constants;
import com.fortran.admin.modules.core.config.mybatis.Page;
import com.fortran.admin.modules.core.exception.ServiceException;
import com.fortran.admin.modules.core.message.RespMsg;
import com.fortran.admin.modules.core.utils.ValidateCodeUtils;
import com.fortran.admin.modules.sys.domain.Menu;
import com.fortran.admin.modules.sys.domain.User;
import com.fortran.admin.modules.sys.service.UserService;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 13:08
 * @description: 用户控制器
 */
@Controller
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    private final String USER_FORM = "modules/sys/userForm";

    private final String USER_LIST = "modules/sys/userList";

    private final String LOGIN = "login";


    //----------------- login -----------------------


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String init(Model model, @ModelAttribute("type") String type, @ModelAttribute("content") String content) {
        model.addAttribute("type", type);
        model.addAttribute("content", content);
        return LOGIN;
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(User user, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {

        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getLoginPwd());
        Subject currentUser = SecurityUtils.getSubject();
        String username = user.getLoginName();
        try {

            String valicateCode = (String) request.getSession().getAttribute(ValidateCodeUtils.VALIDATE_CODE);
            if (Strings.isNullOrEmpty(valicateCode) || !user.getValidateCode().equalsIgnoreCase(valicateCode)) {
                rtnMessage(redirectAttributes, Constants.MSG_TYPE_DANGER, "验证码错误");
                ModelAndView mv = new ModelAndView("redirect:/login");
                return mv;
            }

            log.info("对用户[" + username + "]进行登录验证,验证开始...");
            currentUser.login(token);
            log.info("对用户[" + username + "]进行登录验证,验证通过.");
        } catch (UnknownAccountException uae) {
            log.error("对用户[" + username + "]进行登录验证,验证未通过,未知账户");
            rtnMessage(redirectAttributes, Constants.MSG_TYPE_DANGER, "未知账户");
        } catch (IncorrectCredentialsException ice) {
            log.error("对用户[" + username + "]进行登录验证,验证未通过,错误的凭证");
            rtnMessage(redirectAttributes, Constants.MSG_TYPE_DANGER, "密码不正确");
        } catch (LockedAccountException lae) {
            log.error("对用户[" + username + "]进行登录验证,验证未通过,账户已锁定");
            rtnMessage(redirectAttributes, Constants.MSG_TYPE_DANGER, "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            log.error("对用户[" + username + "]进行登录验证,验证未通过,错误次数过多");
            rtnMessage(redirectAttributes, Constants.MSG_TYPE_DANGER, "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            log.error("对用户[" + username + "]进行登录验证,验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            rtnMessage(redirectAttributes, Constants.MSG_TYPE_DANGER, "用户名或密码不正确");
        }
        // 验证是否登录成功
        if (currentUser.isAuthenticated()) {
            log.info("用户[" + username + "]登录认证通过.");
            redirectAttributes.addFlashAttribute("username", username);
            List<Menu> menus = userService.findPermissionByLoginName(username);
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("username", username);
            mv.addObject("menus", menus);
            return mv;
        } else {
            token.clear();
            ModelAndView mv = new ModelAndView("redirect:/login");
            return mv;
        }
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes) {
        SecurityUtils.getSubject().logout();
        rtnMessage(redirectAttributes, Constants.MSG_TYPE_INFO, "您已安全退出");
        return "redirect:/login";
    }

    //----------------- login end -----------------------


    //----------------- menu -----------------------

    /**
     * 查询一级菜单
     */
    @RequestMapping(value = "/user/getMenu", method = RequestMethod.GET)
    @ResponseBody
    public RespMsg getMenu(@RequestParam("userName") String userName) {
        List<Menu> menus = userService.findPermissionByLoginName(userName);
        return ok(menus);
    }

    /**
     * 查询子菜单
     */
    @RequestMapping(value = "/user/getMenuById", method = RequestMethod.GET)
    @ResponseBody
    public RespMsg getMenuById(@RequestParam("pid") String pid) {
        List<Menu> menus = userService.findMenuByParentId(pid);
        return ok(menus);
    }


    //----------------- menu end -----------------------


    //----------------- user -----------------------

   /* @ModelAttribute
    public User get(@RequestParam(required = false) String id) {
        if (Strings.isNullOrEmpty(id)) {
            return userService.get(id);
        } else {
            return new User();
        }
    }*/

    /**
     * 新增编辑入口
     *
     * @param user
     * @param model
     * @param action @See{com.fortran.admin.modules.core.enumeration.Action}
     * @returnR
     */
    @RequestMapping(value = "form/{action}")
    public String form(Model model, @PathVariable String action,User user) {
        if (!Strings.isNullOrEmpty(action)) model.addAttribute("action", action);
        model.addAttribute("user", user);
        return USER_FORM;
    }

    /**
     * 新增或修改
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/save/{action}/{id}")
    public String saveOrUpdate(User user, Model model, @PathVariable String action, @PathVariable String id) {

        if (!beanValidator(model, user)) {
            return form(model, action,user);
        }
        try {
            userService.saveOrUpdate(user, user != null ? user.getUserId() : null);
            rtnMessage(model, Constants.MSG_TYPE_SUCCESS);
        } catch (ServiceException e) {
            rtnMessage(model, Constants.MSG_TYPE_DANGER);
        }

        return USER_LIST;
    }


    /**
     * 删除
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    public RespMsg delete(Model model, @PathVariable String id) {

        User user = new User();
        try {
            user.setUserId(Long.parseLong(id));
            userService.delete(user);
        } catch (ServiceException e) {
            return error(user);
        }
        return ok(user);

    }

    /**
     * 查询列表
     *
     * @param user
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/user/findAll")
    public String list(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<User> page = userService.findUsers(new Page<User>(request, response), user);
        model.addAttribute("page", page);
        return USER_LIST;
    }


}
