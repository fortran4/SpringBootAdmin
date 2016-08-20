package com.zcoder.admin.modules.core.common;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.zcoder.admin.modules.core.common.constant.Constants;
import com.zcoder.admin.modules.core.message.Msg;
import com.zcoder.admin.modules.core.message.RespMsg;
import com.zcoder.admin.modules.core.message.RespMsgStatus;
import com.zcoder.admin.modules.core.utils.guava.MyLists;
import com.zcoder.admin.modules.core.validator.BeanValidators;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Map;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 13:18
 * @description: 控制器基类
 */
@Slf4j
public class BaseController implements Msg {


    private final String DEFAULT_SORT_FIELD = "id";


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
        for (String message : content) {
            builder.append(message).append(content.length > 1 ? "<br/>" : "");
        }
        model.addAttribute("type", type);
        model.addAttribute("content", builder.toString());
    }

    @Override
    public void rtnMessage(RedirectAttributes redirectAttributes, String type, String... content) {
        StringBuilder builder = new StringBuilder();
        for (String message : content) {
            builder.append(message).append(content.length > 1 ? "<br/>" : "");
        }
        redirectAttributes.addFlashAttribute("type", type);
        redirectAttributes.addFlashAttribute("content", builder.toString());
    }

    @Override
    public RespMsg ok(String content, Object data) {
        if (Strings.isNullOrEmpty(content)) content = Constants.SUCCESS_MSG;
        return new RespMsg(content, data);
    }

    @Override
    public RespMsg ok(Object data) {
        return new RespMsg(Constants.SUCCESS_MSG, data);
    }

    @Override
    public RespMsg error(String content, Object data) {
        if (Strings.isNullOrEmpty(content)) content = Constants.FAIL_MSG;
        return new RespMsg(content, data);
    }

    @Override
    public RespMsg error(Object data) {
        return new RespMsg(RespMsgStatus.ERROR, Constants.FAIL_MSG, null);
    }

    @Override
    public RespMsg rtnMessage(int code, String msg, Object data) {
        return new RespMsg(code, msg, data);
    }


    @Autowired
    protected Validator validator;

    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
     */
    protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
        try {
            BeanValidators.validateWithException(validator, object, groups);
        } catch (ConstraintViolationException ex) {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            log.debug("bean validator exception: {} ", MyLists.toString(list, ":", true));
            rtnMessage(model, MyLists.toString(list, ":", true));
            return false;
        }
        return true;
    }

    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
     */
    protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
        try {
            BeanValidators.validateWithException(validator, object, groups);
        } catch (ConstraintViolationException ex) {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            log.debug("bean validator exception: {} ", MyLists.toString(list, ":", true));
            rtnMessage(redirectAttributes, MyLists.toString(list, ":", true));
            return false;
        }
        return true;
    }

    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组，不传入此参数时，同@Valid注解验证
     * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
     */
    protected void beanValidator(Object object, Class<?>... groups) {
        BeanValidators.validateWithException(validator, object, groups);

    }

    /**
     * 获取参数
     *
     * @param req
     * @return
     */
    protected PageRequest getPage(HttpServletRequest req) {

         int startRow = 1;

         int pageSize = 15;

        String sortField = req.getParameter("columns[0][data]");
        String sortorder = req.getParameter("order[0][dir]");

        if (!Strings.isNullOrEmpty(req.getParameter("start"))) {
            startRow = Integer.parseInt(req.getParameter("start"));
        }
        if (!Strings.isNullOrEmpty(req.getParameter("length"))) {
            pageSize = Integer.parseInt(req.getParameter("length"));
        }


        if (Strings.isNullOrEmpty(sortField)) {
            return new PageRequest(startRow / pageSize, pageSize, Sort.Direction.DESC, DEFAULT_SORT_FIELD);
        } else {
            if ("DESC".equalsIgnoreCase(sortorder)) {
                return new PageRequest(startRow / pageSize, pageSize, Sort.Direction.DESC, sortField);
            } else {
                return new PageRequest(startRow / pageSize, pageSize, Sort.Direction.ASC, sortField);
            }

        }

    }

    /**
     * 构造datatable数据
     * @param page
     */
    protected Map<String,Object> builderDataTable(Page<?> page, HttpServletRequest request){

        Map<String ,Object> result = Maps.newConcurrentMap();
        result.put("data", page.getContent());
        result.put("recordsTotal", page.getTotalElements());
        result.put("recordsFiltered", page.getTotalElements());
        result.put("draw", request.getParameter("draw"));
        return result;

    }


}
