package com.fortran.admin.modules.core.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 自定义错误页面 Created by lin on 2016-05-19.
 */
@Controller
public class ErrorHandleController {

    @RequestMapping(value = "/error/500")
    public String get500Error() {
        return "error/500";
    }

    @RequestMapping(value = "/error/400")
    public String get400Error() {
        return "error/400";
    }

    @RequestMapping(value = "/error/404")
    public String get404Error() {
        return "error/404";
    }

    @RequestMapping(value = "/error/403")
    public String get403Error() {
        return "error/403";
    }

    @RequestMapping(value = "/error/405")
    public String get405Error() {
        return "error/405";
    }
}
