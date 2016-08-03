package com.fortran.admin.modules.core.common;

import com.fortran.admin.modules.core.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: lin
 * @Date: 2016-08-03 Time: 11:33
 * @description: <p>通用功能controller</p>
 */
@Controller
@Slf4j
public class CommonController extends BaseController {

    /**
     * 获取图形验证码
     *
     * @param response HttpResponse
     */
    @RequestMapping(value = "/common/getimagecode")
    public void imageCode(HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            ValidateCodeUtils.createImage(request, response);
        } catch (IOException e) {
            log.error("生成验证码错误...", e);
        }

    }
}