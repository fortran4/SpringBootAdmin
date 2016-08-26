package com.fortran.admin.modules.sys.controller;

import com.fortran.admin.modules.core.common.BaseController;
import com.fortran.admin.modules.core.config.mybatis.Page;
import com.fortran.admin.modules.core.exception.ServiceException;
import com.fortran.admin.modules.core.message.RespMsg;
import com.fortran.admin.modules.sys.domain.Log;
import com.fortran.admin.modules.sys.service.LogService;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>日志控制器</p>
 * Created by lin on 16/8/9.
 */
@Controller
@Slf4j
public class LogController extends BaseController {

    @Autowired
    private LogService logService;

    private final String LOG_FORM = "modules/sys/logForm";

    private final String LOG_LIST = "modules/sys/logList";


    /**
     * 新增编辑
     *
     * @param log
     * @param model
     * @param action @See{com.fortran.admin.modules.core.enumeration.Action}
     * @returnR
     */
    @RequestMapping(value = "/log/form/{action}")
    public String form(Model model, @PathVariable String action, Log log) {
        if (!Strings.isNullOrEmpty(action)) model.addAttribute("action", action);
        model.addAttribute("Log", log);
        return LOG_FORM;
    }


    /**
     * 查询列表
     *
     * @param log
     * @param model
     * @return
     */
    @RequestMapping(value = "/log/findAll")
    public String list(HttpServletRequest request,Model model, Log log) {
        Page<Log> page = logService.findLogs(Page.getInstance(request,log));
        model.addAttribute("page",page);
        return LOG_LIST;
    }


    /**
     * 删除
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/log/delete/{id}")
    public RespMsg delete(Model model, @PathVariable String id) {

        Log log = new Log();
        try {
            log.setLogId(Long.parseLong(id));
            logService.delete(log);
        } catch (ServiceException e) {
            return error(log);
        }
        return ok(log);

    }

}
