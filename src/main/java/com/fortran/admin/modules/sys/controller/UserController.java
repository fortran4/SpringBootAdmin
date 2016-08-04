package com.fortran.admin.modules.sys.controller;

import com.fortran.admin.modules.core.common.BaseController;
import com.fortran.admin.modules.core.common.constant.Constants;
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String init(Model model, @ModelAttribute("type") String type, @ModelAttribute("content") String content) {
        model.addAttribute("type", type);
        model.addAttribute("content", content);
        return "login";
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(User user, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {

        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getLoginPwd());
        Subject currentUser = SecurityUtils.getSubject();
        String username = user.getLoginName();
        try {

            String valicateCode = (String)request.getSession().getAttribute(ValidateCodeUtils.VALIDATE_CODE);
            if (Strings.isNullOrEmpty(valicateCode) || !user.getValidateCode().equalsIgnoreCase(valicateCode)){
                rtnMessage(redirectAttributes, Constants.MSG_TYPE_DANGER,"验证码错误");
                ModelAndView mv = new ModelAndView("redirect:/login");
                return mv;
            }

            log.info("对用户[" + username + "]进行登录验证,验证开始...");
            currentUser.login(token);
            log.info("对用户[" + username + "]进行登录验证,验证通过.");
        } catch (UnknownAccountException uae) {
            log.error("对用户[" + username + "]进行登录验证,验证未通过,未知账户");
            rtnMessage(redirectAttributes, Constants.MSG_TYPE_DANGER,"未知账户");
        } catch (IncorrectCredentialsException ice) {
            log.error("对用户[" + username + "]进行登录验证,验证未通过,错误的凭证");
            rtnMessage(redirectAttributes, Constants.MSG_TYPE_DANGER,"密码不正确");
        } catch (LockedAccountException lae) {
            log.error("对用户[" + username + "]进行登录验证,验证未通过,账户已锁定");
            rtnMessage(redirectAttributes, Constants.MSG_TYPE_DANGER,"账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            log.error("对用户[" + username + "]进行登录验证,验证未通过,错误次数过多");
            rtnMessage(redirectAttributes, Constants.MSG_TYPE_DANGER,"用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            log.error("对用户[" + username + "]进行登录验证,验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            rtnMessage(redirectAttributes, Constants.MSG_TYPE_DANGER,"用户名或密码不正确");
        }
        // 验证是否登录成功
        if (currentUser.isAuthenticated()) {
            log.info("用户[" + username + "]登录认证通过.");
            redirectAttributes.addFlashAttribute("username",username);
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("username",username);
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
        rtnMessage(redirectAttributes, Constants.MSG_TYPE_INFO,"您已安全退出");
        return "redirect:/login";
    }

    /**
     * 查询菜单
     */
    @RequestMapping(value = "/user/getMenu", method = RequestMethod.GET)
    @ResponseBody
    public RespMsg getMenu(@RequestParam("userName") String userName){
        List<Menu> menus = userService.findPermissionByLoginName(userName);
        return ok(menus);
    }

}
