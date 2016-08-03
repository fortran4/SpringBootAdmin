package com.fortran.admin.modules.core.common;

import com.fortran.admin.modules.core.common.constant.Constants;
import com.fortran.admin.modules.core.message.Msg;
import com.fortran.admin.modules.core.message.RespMsg;
import com.fortran.admin.modules.core.message.RespMsgStatus;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 13:18
 * @description: 控制器基类
 */
public class BaseController implements Msg {


    /**
     * <p>初始化数据绑定</p>
     * <p>将所有传递进来的String进行HTML编码，防止XSS攻击</p>
     * <p>将字段中Date类型转换为String类型</p>
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }

            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
       /* // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });*/
    }


    @Override
    public void rtnMessage(Model model, String type, String... content) {
        StringBuilder builder = new StringBuilder();
        for (String message : content){
            builder.append(message).append(content.length>1?"<br/>":"");
        }
        model.addAttribute("type", type);
        model.addAttribute("content", builder.toString());
    }

    @Override
    public void rtnMessage(RedirectAttributes redirectAttributes, String type, String... content) {
        StringBuilder builder = new StringBuilder();
        for (String message : content){
            builder.append(message).append(content.length>1?"<br/>":"");
        }
        redirectAttributes.addFlashAttribute("type", type);
        redirectAttributes.addFlashAttribute("content", builder.toString());
    }

    @Override
    public RespMsg ok(String content, Object data) {
        if (Strings.isNullOrEmpty(content)) content = Constants.SUCCESS_MSG;
        return new RespMsg(content,data);
    }

    @Override
    public RespMsg ok(Object data) {
        return new RespMsg(Constants.SUCCESS_MSG,data);
    }

    @Override
    public RespMsg error(String content) {
        return new RespMsg(RespMsgStatus.ERROR,Constants.FAIL_MSG,null);
    }

    @Override
    public RespMsg rtnMessage(int code, String msg, Object data) {
        return new RespMsg(code,msg,data);
    }
}
